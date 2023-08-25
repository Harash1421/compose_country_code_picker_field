package com.harash1421.country_code_picker_field.utils

import android.telephony.PhoneNumberUtils
import android.text.Selection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.*

class RealTimePhoneNumberFormatter(countryCode: String = Locale.getDefault().country) :
    VisualTransformation {

    private val phoneNumberFormatter = PhoneNumberUtil.getInstance().getAsYouTypeFormatter(countryCode)

    override fun filter(text: AnnotatedString): TransformedText {
        val transformation = reformat(text, Selection.getSelectionEnd(text))

        return TransformedText(
            AnnotatedString(transformation.formatted ?: ""),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return transformation.originalToTransformed.getOrElse(offset) {
                        transformation.transformedToOriginal.lastIndex
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return transformation.transformedToOriginal[offset]
                }
            }
        )
    }

    /**
     * Reformats the phone number and maps cursor positions.
     */
    private fun reformat(s: CharSequence, cursor: Int): Transformation {
        phoneNumberFormatter.clear()

        var formatted: String? = null
        var lastNonSeparator: Char? = null
        var hasCursor = false

        s.forEachIndexed { index, char ->
            if (PhoneNumberUtils.isNonSeparator(char)) {
                lastNonSeparator?.let {
                    formatted = getFormattedNumber(it, hasCursor)
                }
                hasCursor = false
                lastNonSeparator = char
            }
            if (index == cursor - 1) {
                hasCursor = true
            }
        }

        lastNonSeparator?.let {
            formatted = getFormattedNumber(it, hasCursor)
        }

        val (originalToTransformed, transformedToOriginal) = mapCursorPositions(formatted)

        return Transformation(formatted, originalToTransformed, transformedToOriginal)
    }

    /**
     * Maps the cursor positions for the original and transformed texts.
     */
    private fun mapCursorPositions(formatted: String?): Pair<List<Int>, List<Int>> {
        val originalToTransformed = mutableListOf<Int>()
        val transformedToOriginal = mutableListOf<Int>()
        var specialCharsCount = 0

        formatted?.forEachIndexed { index, char ->
            if (!PhoneNumberUtils.isNonSeparator(char)) {
                specialCharsCount++
            } else {
                originalToTransformed.add(index)
            }
            transformedToOriginal.add(index - specialCharsCount)
        }
        originalToTransformed.add(originalToTransformed.maxOrNull()?.plus(1) ?: 0)
        transformedToOriginal.add(transformedToOriginal.maxOrNull()?.plus(1) ?: 0)

        return Pair(originalToTransformed, transformedToOriginal)
    }

    /**
     * Gets the formatted version of the number with the given last non-separator character.
     */
    private fun getFormattedNumber(lastNonSeparator: Char, hasCursor: Boolean): String? {
        return if (hasCursor) {
            phoneNumberFormatter.inputDigitAndRememberPosition(lastNonSeparator)
        } else {
            phoneNumberFormatter.inputDigit(lastNonSeparator)
        }
    }

    /**
     * Data class that holds transformation details.
     */
    private data class Transformation(
        val formatted: String?,
        val originalToTransformed: List<Int>,
        val transformedToOriginal: List<Int>
    )
}
package org.wordpress.aztec.spans

import android.graphics.Color
import android.text.TextPaint
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import org.wordpress.aztec.AztecAttributes

class AztecColorSpan(
        val color: Int,
        val alpha: Int = 220,
        tag: String = "span",
        override var attributes: AztecAttributes = AztecAttributes()
) : ForegroundColorSpan(color), IAztecInlineSpan {

    fun getColorHex(): String {
        return java.lang.String.format("#%06X", 0xFFFFFF and color)
    }

    override val TAG = tag
}
package org.wordpress.aztec.plugins

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import org.wordpress.aztec.plugins.html2visual.ISpanPostprocessor
import org.wordpress.aztec.plugins.visual2html.ISpanPreprocessor
import org.wordpress.aztec.source.CssStyleFormatter
import org.wordpress.aztec.spans.AztecBackgroundColorSpan

class CssBackgroundColorPlugin: ISpanPreprocessor {

    private val SPAN_TAG = "span"

    override fun beforeSpansProcessed(spannable: SpannableStringBuilder) {
        spannable.getSpans(0, spannable.length, AztecBackgroundColorSpan::class.java).forEach {
            if (!CssStyleFormatter.containsStyleAttribute(it.attributes, CssStyleFormatter.CSS_BACKGROUND_COLOR_ATTRIBUTE)) {
                CssStyleFormatter.addStyleAttribute(it.attributes, CssStyleFormatter.CSS_BACKGROUND_COLOR_ATTRIBUTE, it.getColorHex())
            }
        }
    }
}
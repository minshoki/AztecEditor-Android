package org.wordpress.aztec.plugins

import android.text.SpannableStringBuilder
import android.text.Spanned
import org.wordpress.aztec.plugins.visual2html.ISpanPreprocessor
import org.wordpress.aztec.source.CssStyleFormatter
import org.wordpress.aztec.spans.AztecColorSpan
import org.wordpress.aztec.spans.HiddenHtmlSpan
import org.wordpress.aztec.spans.IAztecNestable
import org.wordpress.aztec.util.SpanWrapper

class CssColorPlugin: ISpanPreprocessor {

    private val SPAN_TAG = "span"

    override fun beforeSpansProcessed(spannable: SpannableStringBuilder) {
        spannable.getSpans(0, spannable.length, AztecColorSpan::class.java).forEach {
            if (!CssStyleFormatter.containsStyleAttribute(it.attributes, CssStyleFormatter.CSS_COLOR_ATTRIBUTE)) {
                CssStyleFormatter.addStyleAttribute(it.attributes, CssStyleFormatter.CSS_COLOR_ATTRIBUTE, it.getColorHex())
            }

//            val start = spannable.getSpanStart(it)
//            val nesting = IAztecNestable.getNestingLevelAt(spannable, start) + 1
//
//            val calypsoUnderlineSpan = HiddenHtmlSpan(SPAN_TAG, it.attributes, nesting)
//
//            spannable.setSpan(calypsoUnderlineSpan, start, spannable.getSpanEnd(it), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            spannable.removeSpan(it)
//
//            // if a parent of the new hidden span is also a <span> we can merge them
//            IAztecNestable.getParent(spannable, SpanWrapper(spannable, calypsoUnderlineSpan))?.let {
//                if (it.span is HiddenHtmlSpan) {
//                    val hiddenSpan = it.span as HiddenHtmlSpan
//                    if (hiddenSpan.TAG == SPAN_TAG) {
//                        val parentStyle = hiddenSpan.attributes.getValue(CssStyleFormatter.STYLE_ATTRIBUTE)
//                        val childStyle = calypsoUnderlineSpan.attributes.getValue(CssStyleFormatter.STYLE_ATTRIBUTE)
//                        if (parentStyle != null && childStyle != null) {
//                            hiddenSpan.attributes.setValue(CssStyleFormatter.STYLE_ATTRIBUTE, CssStyleFormatter.mergeStyleAttributes(parentStyle, childStyle))
//                        }
//
//                        // remove the extra child span
//                        spannable.removeSpan(calypsoUnderlineSpan)
//                    }
//                }
//            }
        }
    }
}
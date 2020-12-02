package org.wordpress.aztec.demo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.AztecAttributes
import org.wordpress.aztec.AztecText
import org.wordpress.aztec.AztecTextFormat
import org.wordpress.aztec.extensions.getMediaLinkAttributes
import org.wordpress.aztec.glideloader.GlideImageLoader
import org.wordpress.aztec.glideloader.GlideVideoThumbnailLoader
import org.wordpress.aztec.plugins.BackgroundColorButton
import org.wordpress.aztec.plugins.ColorButton
import org.wordpress.aztec.plugins.CssBackgroundColorPlugin
import org.wordpress.aztec.plugins.CssColorPlugin
import org.wordpress.aztec.source.CssStyleFormatter
import org.wordpress.aztec.util.ColorConverter
import org.xml.sax.Attributes
import kotlin.random.Random

class TestActivity: AppCompatActivity() {

    private val aztec: AztecText by lazy { findViewById<AztecText>(R.id.aztec) }
    private val boldButton: Button by lazy { findViewById<Button>(R.id.btn_b) }
    private val italicButton: Button by lazy { findViewById<Button>(R.id.btn_i) }
    private val colorButton: Button by lazy { findViewById<Button>(R.id.btn_color) }
    private val bgColorButton: Button by lazy { findViewById<Button>(R.id.btn_bg_color) }
    private val preview: TextView by lazy { findViewById<TextView>(R.id.preview) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        kotlin.run {
            aztec.plugins.add(CssBackgroundColorPlugin())
            aztec.plugins.add(BackgroundColorButton(aztec))
            aztec.plugins.add(CssColorPlugin())
            aztec.plugins.add(ColorButton(aztec))

            boldButton.setOnClickListener {
                aztec.toggleFormatting(AztecTextFormat.FORMAT_BOLD)
                aztec.toggleFormatting(AztecTextFormat.FORMAT_UNDERLINE)
                preview.text = aztec.toHtml()
            }

            italicButton.setOnClickListener {
                aztec.toggleFormatting(AztecTextFormat.FORMAT_ITALIC)
                preview.text = aztec.toHtml()
            }

            aztec.setOnSelectionChangedListener(object: AztecText.OnSelectionChangedListener {
                override fun onSelectionChanged(selStart: Int, selEnd: Int) {
                    val stylePredicate = object : AztecText.AttributePredicate {
                        override fun matches(attrs: Attributes): Boolean {
                            return attrs.getIndex("style") > -1
                        }
                    }

                    aztec.getElementAttributesWithSelection(stylePredicate).forEach { attr ->
                        val backgroundColorAttr = CssStyleFormatter.getStyleAttribute(attr, CssStyleFormatter.CSS_BACKGROUND_COLOR_ATTRIBUTE)
                    }
                }
            })

            colorButton.setOnClickListener {
                val randomColor = listOf(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA)
                aztec.setTextSpanColor(randomColor.random())
                aztec.inlineFormatter.applyInlineStyle(AztecTextFormat.FORMAT_COLOR)
                preview.text = aztec.toHtml()
            }

            bgColorButton.setOnClickListener {
                val randomColor = listOf(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA)
                aztec.setBackgroundSpanColor(randomColor.random())
                aztec.inlineFormatter.applyInlineStyle(AztecTextFormat.FORMAT_BACKGROUND)
                preview.text = aztec.toHtml()
            }
        }
    }
}
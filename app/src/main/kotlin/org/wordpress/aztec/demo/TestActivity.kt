package org.wordpress.aztec.demo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.AztecAttributes
import org.wordpress.aztec.AztecText
import org.wordpress.aztec.AztecTextFormat
import org.wordpress.aztec.glideloader.GlideImageLoader
import org.wordpress.aztec.glideloader.GlideVideoThumbnailLoader
import org.wordpress.aztec.plugins.BackgroundColorButton
import org.wordpress.aztec.plugins.CssBackgroundColorPlugin
import org.wordpress.aztec.source.CssStyleFormatter
import kotlin.random.Random

class TestActivity: AppCompatActivity() {

    private val aztec: AztecText by lazy { findViewById<AztecText>(R.id.aztec) }
    private val boldButton: Button by lazy { findViewById<Button>(R.id.btn_b) }
    private val italicButton: Button by lazy { findViewById<Button>(R.id.btn_i) }
    private val colorButton: Button by lazy { findViewById<Button>(R.id.btn_color) }
    private val preview: TextView by lazy { findViewById<TextView>(R.id.preview) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        kotlin.run {
            boldButton.setOnClickListener {
                aztec.toggleFormatting(AztecTextFormat.FORMAT_BOLD)
                preview.text = aztec.toHtml()
            }

            italicButton.setOnClickListener {
                aztec.toggleFormatting(AztecTextFormat.FORMAT_ITALIC)
                preview.text = aztec.toHtml()
            }
            aztec.plugins.add(CssBackgroundColorPlugin())
            aztec.plugins.add(BackgroundColorButton(aztec))

            colorButton.setOnClickListener {
                aztec.setBackgroundSpanColor(resources.getColor(R.color.design_default_color_primary))
                aztec.toggleFormatting(AztecTextFormat.FORMAT_BACKGROUND)
                preview.text = aztec.toHtml()
//                aztec.getAppliedStyles(aztec.selectionStart, aztec.selectionEnd).forEach {
//                    Log.e("shokitest", "style ${it.name}")
//                }
//                aztec.selectedStyles.forEach {
//                    Log.e("shokitest", it.name)
//                }
//                aztec.inlineFormatter.applyInlineStyle(, attrs = AztecAttributes().apply {
//                    setValue("test", Random.nextInt(1, 10).toString())
//                })
//                preview.text = aztec.toFormattedHtml()
            }
        }
    }
}
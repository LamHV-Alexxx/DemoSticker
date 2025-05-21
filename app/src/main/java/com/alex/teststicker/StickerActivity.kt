package com.alex.teststicker

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.PhotoFilter
import ja.burhanrashid52.photoeditor.TextStyleBuilder
import ja.burhanrashid52.photoeditor.ViewType

class StickerActivity : AppCompatActivity() {
    private var mainFeature: LinearLayout? = null
    private var textFeature: TextView? = null
    private var filterFeature: TextView? = null
    private var stickerFeature: TextView? = null
    private var detailFilter: LinearLayout? = null
    private var noneFil: TextView? = null
    private var autoFil: TextView? = null
    private var brightnessFil: TextView? = null
    private var detailSticker: LinearLayout? = null
    private var sticker1: ImageView? = null
    private var sticker2: ImageView? = null
    private var sticker3: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sticker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()


        val editText = findViewById<EditText>(R.id.editText)

        // Gắn ảnh vào editor view
        val photoEditorView = findViewById<PhotoEditorView>(R.id.photoEditorView)
        photoEditorView.source.scaleType = ImageView.ScaleType.CENTER_CROP

        photoEditorView.source.setImageResource(R.drawable.model_wears_top_with_long_sleeved_to_long_skirt)

        val photoEditor = PhotoEditor.Builder(this, photoEditorView)
            .setPinchTextScalable(true) // Zoom text/sticker
            .build()

        photoEditor.setFilterEffect(PhotoFilter.NONE)

        textFeature?.setOnClickListener {
            mainFeature?.let {
                it.visibility = View.INVISIBLE
            }

            photoEditor.addText(
                text = "Hello, World!",
                colorCodeTextView = Color.RED
            )
        }

        photoEditor.setOnPhotoEditorListener(object : OnPhotoEditorListener {
            override fun onAddViewListener(viewType: ViewType, numberOfAddedViews: Int) {

            }

            override fun onEditTextChangeListener(rootView: View, text: String, colorCode: Int) {
                editText.visibility = View.VISIBLE
                editText.setText(text)
                editText.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        val editedText = editText.text.toString()
                        photoEditor.editText(
                            view = rootView,
                            inputText = editedText,
                            styleBuilder = TextStyleBuilder().apply {
                                withTextFont(Typeface.DEFAULT)
                                withTextSize(25f)
                                withTextColor(Color.YELLOW)
                        })

                        editText.clearFocus()
                        editText.visibility = View.INVISIBLE
                        mainFeature?.visibility = View.VISIBLE

                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(editText.windowToken, 0)

                        true  // Đã xử lý sự kiện
                    } else {
                        false  // Không xử lý sự kiện khác
                    }
                }
            }

            override fun onRemoveViewListener(viewType: ViewType, numberOfAddedViews: Int) {
                mainFeature?.visibility = View.VISIBLE
            }

            override fun onStartViewChangeListener(viewType: ViewType) {

            }

            override fun onStopViewChangeListener(viewType: ViewType) {

            }

            override fun onTouchSourceImage(event: MotionEvent) {
                mainFeature?.visibility = View.VISIBLE
                detailFilter?.visibility = View.INVISIBLE
                detailSticker?.visibility = View.INVISIBLE
            }
        })

        // TODO: Filter feature
        filterFeature?.setOnClickListener {
            mainFeature?.let {
                it.visibility = View.INVISIBLE
            }
            detailFilter?.let {
                it.visibility = View.VISIBLE
            }
        }

        noneFil?.setOnClickListener {
            photoEditor.setFilterEffect(PhotoFilter.NONE)
        }

        autoFil?.setOnClickListener {
            photoEditor.setFilterEffect(PhotoFilter.AUTO_FIX)
        }

        brightnessFil?.setOnClickListener {
            photoEditor.setFilterEffect(PhotoFilter.BRIGHTNESS)
        }

        // TODO: Sticker feature
        stickerFeature?.setOnClickListener {
            mainFeature?.let {
                it.visibility = View.INVISIBLE
            }
            detailSticker?.let {
                it.visibility = View.VISIBLE
            }
        }

        sticker1?.setOnClickListener {
            val stickerDrawable = ContextCompat.getDrawable(this, R.drawable.sticker1)
            stickerDrawable?.let {
                photoEditor.addImage(it.toBitmap())
            }
        }

        sticker2?.setOnClickListener {
            val stickerDrawable = ContextCompat.getDrawable(this, R.drawable.sticker2)
            stickerDrawable?.let {
                photoEditor.addImage(it.toBitmap())
            }
        }

        sticker3?.setOnClickListener {
            val stickerDrawable = ContextCompat.getDrawable(this, R.drawable.sticker3)
            stickerDrawable?.let {
                photoEditor.addImage(it.toBitmap())
            }
        }
    }

    private fun initView() {
        mainFeature = findViewById(R.id.mainFeature)
        textFeature = findViewById(R.id.textFeature)
        filterFeature = findViewById(R.id.filterFeature)
        stickerFeature = findViewById(R.id.stickerFeature)
        detailFilter = findViewById(R.id.detailFilter)
        noneFil = findViewById(R.id.noneFil)
        autoFil = findViewById(R.id.autoFil)
        brightnessFil = findViewById(R.id.brightnessFil)
        detailSticker = findViewById(R.id.detailSticker)
        sticker1 = findViewById(R.id.sticker1)
        sticker2 = findViewById(R.id.sticker2)
        sticker3 = findViewById(R.id.sticker3)
    }
}
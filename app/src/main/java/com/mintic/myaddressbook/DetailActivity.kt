package com.mintic.myaddressbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d(TAG, "onCreate")
        val name = intent.getStringExtra(ListActivity.KEY_NAME)
        val description = intent.getStringExtra(ListActivity.KEY_DESCRIPTION)
        val points = intent.getStringExtra(ListActivity.KEY_POINTS)
        val image = intent.getStringExtra(ListActivity.KEY_FOTO)

        val textView = findViewById<TextView>(R.id.textView3).apply { text = name }
        val imageView = findViewById<ImageView>(R.id.imageView2).apply {
            Glide.with(context)
                .load(image)
                .into(this)
        }
        val textView1 = findViewById<TextView>(R.id.textView4).apply { text = description }

    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
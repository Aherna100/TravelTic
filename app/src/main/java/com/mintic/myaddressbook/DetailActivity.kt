package com.mintic.myaddressbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        createRatingBar()

        val name = intent.getStringExtra(ListActivity.KEY_NAME)
        val description = intent.getStringExtra(ListActivity.KEY_INFO)
        //val points = intent.getStringExtra(ListActivity.KEY_POINTS)
        val image = intent.getStringExtra(ListActivity.KEY_FOTO)
        val temp = intent.getStringExtra(ListActivity.KEY_TEMPERATURE)
        val location = intent.getStringExtra(ListActivity.KEY_LOCATION)

        val textView = findViewById<TextView>(R.id.textView3).apply { text = name }
        val imageView = findViewById<ImageView>(R.id.imageView2).apply {
            Glide.with(context)
                .load(image)
                .into(this)
        }
        val textView1 = findViewById<TextView>(R.id.textView4).apply { text = description }
        val textView2 = findViewById<TextView>(R.id.textView2).apply { text = temp }
        val textView3 = findViewById<TextView>(R.id.textView).apply { text = location }

    }

    private fun createRatingBar() {
        val root: ConstraintLayout = findViewById(R.id.constraintS)

        val points = intent.getStringExtra(ListActivity.KEY_POINTS)

        val strgs: String? = points.toString()
        val flts: Float? = strgs?.toFloat()

        val ratingBar: RatingBar = RatingBar(this).apply {
            setIsIndicator(true)
            numStars = 5
            stepSize = 1.0f
            if (flts != null) {
                rating = flts
            }
            //rating = randomBetweenOneAndFive()
            max = 5

            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val ratingBarIndexInLayout = 3
        root.addView(ratingBar, ratingBarIndexInLayout)
    }

    private fun randomBetweenOneAndFive() = Random.nextInt(1, 5).toFloat()

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
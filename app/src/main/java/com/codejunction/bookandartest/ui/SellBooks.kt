package com.codejunction.bookandartest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.codejunction.bookandartest.R
import kotlinx.android.synthetic.main.activity_sell_books.*

class SellBooks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_books)

        sellBackBtn.setOnClickListener {
            onBackPressed()
        }

        //Area List
        val area = arrayOf(
            "Uttam Nagar", "Dawarka", "West Delhi", "North Delhi", "South Delhi"
        )

        val areaAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.list_item, area
        )
        tvAreaOfBook.setAdapter(areaAdapter)

        //Genre List
        val genre = arrayOf(
            "Comics", "Novel", "Horror", "Sci-Fi", "Crime","School","College")
        val genreAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.list_item, genre
        )
        tvGenre.setAdapter(genreAdapter)

        radioSellBtn.setOnClickListener {
            bookPrice.visibility= View.VISIBLE
        }

        radioDonateBtn.setOnClickListener {
            bookPrice.visibility= View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
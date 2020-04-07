package com.devfk.ma.topstory.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devfk.ma.topstory.R
import kotlinx.android.synthetic.main.app_bar.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialization()
    }

    private fun initialization() {
        headerText.text = resources.getString(R.string.Detail_header)
    }
}

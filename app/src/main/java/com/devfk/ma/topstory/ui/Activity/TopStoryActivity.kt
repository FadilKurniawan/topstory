package com.devfk.ma.topstory.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.devfk.ma.topstory.R
import com.devfk.ma.topstory.data.`interface`.IStory
import com.devfk.ma.topstory.data.model.Story
import com.devfk.ma.topstory.data.presenter.StoryPresenter
import kotlinx.android.synthetic.main.app_bar.*

class TopStoryActivity : AppCompatActivity(),IStory {
    lateinit var txt: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById<TextView>(R.id.txt)

        headerText.text = resources.getString(R.string.Main_header)
        StoryPresenter(this).getDataList()

    }

    override fun onIdList(idStory: List<Int>?) {
        if (idStory != null) {
            StoryPresenter(this).getListDetail(idStory)
        }else{
            Toast.makeText(this,"response 1 null ",Toast.LENGTH_LONG).show()
        }
    }

    override fun onStoryList(listStory: Story?) {
        txt.text = listStory?.title
    }

    override fun onDataError(throwable: Throwable) {
        Toast.makeText(this,"Error: $throwable",Toast.LENGTH_LONG).show()
    }
}

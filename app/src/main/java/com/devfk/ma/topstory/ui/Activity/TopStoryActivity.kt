package com.devfk.ma.topstory.ui.Activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devfk.ma.topstory.R
import com.devfk.ma.topstory.data.`interface`.IStory
import com.devfk.ma.topstory.data.model.Story
import com.devfk.ma.topstory.data.presenter.StoryPresenter
import com.devfk.ma.topstory.ui.Adapter.StoryBaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*


class TopStoryActivity : AppCompatActivity(),IStory {
    lateinit var txt: TextView
    var getItemFromIndex: Int =0
    var getItemToIndex:Int = 5
    var ListStory:ArrayList<Story> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
    }

    private fun initialization() {
        progress_horizontal.visibility = View.GONE
        headerText.text = resources.getString(R.string.Main_header)
        StoryPresenter(this).getDataList()
        txtFavorite.setOnClickListener {
            StoryPresenter(this).getDataList()
        }
    }

    override fun onIdList(idStory: List<Int>?) {
        if (idStory != null) {
            StoryPresenter(this).getListDetail(idStory,getItemFromIndex,getItemToIndex)
            getItemFromIndex+=6
            getItemToIndex+=6
        }else{
            Toast.makeText(this,"response Error ",Toast.LENGTH_LONG).show()
        }
    }

    override fun onStoryList(listStory: ArrayList<Story>) {
        for(i in listStory){
            ListStory.add(i)
        }
        gridView.adapter = StoryBaseAdapter(ListStory)
        scrollMygridViewToBottom()

    }
    private fun scrollMygridViewToBottom()
    {
        gridView.post(Runnable () {
            @Override
            fun run() {
                // Select the last row so it will scroll into view...
                gridView.setSelection(gridView.count - 1)
            }
        })
    }
    override fun onDataError(throwable: Throwable) {
        Toast.makeText(this,"Error: $throwable",Toast.LENGTH_LONG).show()
    }

}

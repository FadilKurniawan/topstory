package com.devfk.ma.topstory.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.devfk.ma.topstory.R
import com.devfk.ma.topstory.data.model.Story
import com.devfk.ma.topstory.ui.Activity.DetailActivity


class StoryBaseAdapter(nameItem: ArrayList<Story>) : BaseAdapter(){
    private val item = nameItem

    @SuppressLint("ViewHolder")
    override fun getView(position:Int, convertView: View?, parent: ViewGroup?):View{
        // Inflate the custom view
        val inflater = parent?.context?.
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view    = inflater.inflate(R.layout.card_item_stories,null)
        // Get the custom view widgets reference
        val title = view.findViewById<TextView>(R.id.txtJudul)
        val score = view.findViewById<TextView>(R.id.txtScore)
        val comment = view.findViewById<TextView>(R.id.txtComment)
        val card = view.findViewById<CardView>(R.id.card_view)

        // Display color name on text view
        if(item[position].title.length> 50){
            title.text = item[position].title.substring(0,50)+"..."
        }else{
            title.text = item[position].title
        }

        score.text = item[position].score.toString()

        if(item[position].kids!=null){
            comment.text = item[position].kids.size.toString()
        }else{
            comment.text="0"
        }


        // Set Icon for card view
        card.setOnClickListener{
            val myIntent = Intent(parent?.context, DetailActivity::class.java)
            parent?.context?.startActivity(myIntent)
        }
        return view
    }

    override fun getItem(position: Int): Any? {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Count the items
    override fun getCount(): Int {
        return item.size
    }

}
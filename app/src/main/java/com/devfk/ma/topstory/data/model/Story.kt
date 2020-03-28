package com.devfk.ma.topstory.data.model


import com.google.gson.annotations.SerializedName

data class Story(
    val `by`: String,
    val descendants: Int,
    val id: Int,
    val kids: List<Int>,
    val score: Int,
    val time: Int,
    val title: String,
    val type: String,
    val url: String
)
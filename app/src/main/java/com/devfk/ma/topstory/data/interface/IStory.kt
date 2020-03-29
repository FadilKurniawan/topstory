package com.devfk.ma.topstory.data.`interface`

import com.devfk.ma.topstory.data.model.Story

interface IStory{
    fun onIdList(idStory: List<Int>?)
    fun onStoryList(listStory: ArrayList<Story>)
    fun onDataError(throwable: Throwable)
}
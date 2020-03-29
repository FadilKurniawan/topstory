package com.devfk.ma.topstory.data.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.devfk.ma.topstory.data.`interface`.IStory
import com.devfk.ma.topstory.data.model.Story
import com.devfk.ma.topstory.data.service.APIService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryPresenter(context: Context){
    val storyView = context as IStory

    fun getDataList() {
        APIService.create()
            .getIdList().enqueue(object : Callback<List<Int>>{
                override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                    storyView.onDataError(t)
                }

                override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                    storyView.onIdList(response.body())
                }

            })
    }

    @SuppressLint("CheckResult")
    fun getListDetail(listid: List<Int>,from:Int,to:Int) {

        var api = APIService.create()
        var listStory: ArrayList<Story> = ArrayList()

        Observable.fromIterable(listid.subList(from,to+1))
            .flatMapCompletable{
                api.getItemDetail(it).doOnNext {
                    listStory.add(it)
                }.ignoreElements()
            }
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                storyView.onStoryList(listStory)
            }, {
                println(it.message)
             }
            )

    }

}


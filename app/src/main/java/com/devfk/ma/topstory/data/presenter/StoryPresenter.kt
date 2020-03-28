package com.devfk.ma.topstory.data.presenter

import android.content.Context
import androidx.annotation.IntegerRes
import com.devfk.ma.topstory.data.`interface`.IStory
import com.devfk.ma.topstory.data.service.APIService
import com.rx2androidnetworking.Rx2AndroidNetworking
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

    fun getListDetail(listStory: List<Int>) {
        var api = APIService.create()
        println("** Started ${listStory.size} length")
//        val requests: MutableList<Observable<*>> = ArrayList()
//        val call1: Observable<List<Story>>
//
//        for (i in listStory.indices){
//            requests.add(APIService.create().getItemDetail(listStory[i]))
//        }
        var i=1
        Observable.fromIterable(listStory)
            .flatMapCompletable { api.getItemDetail(it).doOnComplete {
                println("** complete $i")
                i++
            }.doOnError { it ->
                println("** Error $i = ${it.message}")
                i++
            } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("** completed")
            }, {
                println(it.message)
             }
            )
        println("** completed")


    }
    private fun getAllMyFriendsObservable(): Observable<List<Int?>?>? {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFriends/{userId}")
            .addPathParameter("userId", "1")
            .build()
            .getObjectListObservable(Int)
    }

}


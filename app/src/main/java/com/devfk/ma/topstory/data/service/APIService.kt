package com.devfk.ma.topstory.data.service

import com.devfk.ma.topstory.data.util.Constant
import io.reactivex.Completable
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService{

    //getList ID
    @GET("topstories.json")
    fun getIdList(): Call<List<Int>>

    @GET("item/{Id}.json")
    fun getItemDetail(@Path("Id") Id:Int): Completable

    companion object Factory {
        fun create(): APIService{
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)
        }
    }

}
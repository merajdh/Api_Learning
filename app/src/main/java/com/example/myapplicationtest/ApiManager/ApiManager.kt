package com.example.myapplicationtest.ApiManager

import com.example.myapplicationtest.Model.News
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val API_KEY = "authorization: Apikey d98e82105d0bc4108702d6a778a1f50810d93f31454f4d20471b64bb2ac55563"

class ApiManager  {

    private val ApiService : apiService

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ApiService = retrofit.create(apiService::class.java)

    }

    fun getNews(callback : ApiCallback<ArrayList<Pair<String , String>>>){

        ApiService.getTopNews().enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val data = response.body()!!
                val DataToSend : ArrayList <Pair<String , String>> = arrayListOf()
                data.data.forEach {
                    DataToSend.add(Pair(it.title , it.url))
                }
                callback.onSuccess( DataToSend )

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                callback.onError(t.message!!)
            }


        })

    }

    interface ApiCallback<T>{
        fun onSuccess(data: ArrayList<Pair<String, String>>)
        fun onError(errorManager: String)
    }

}
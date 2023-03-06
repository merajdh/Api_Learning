package com.example.myapplicationtest.ApiManager

import com.example.myapplicationtest.Model.Chart
import com.example.myapplicationtest.Model.News
import com.example.myapplicationtest.Model.TopCoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val API_KEY =
    "authorization: Apikey d98e82105d0bc4108702d6a778a1f50810d93f31454f4d20471b64bb2ac55563"

class ApiManager {

    private val ApiService: apiService

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ApiService = retrofit.create(apiService::class.java)

    }

    fun getNews(callback: ApiCallback<ArrayList<Pair<String, String>>>) {

        ApiService.getTopNews().enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val data = response.body()!!
                val DataToSend: ArrayList<Pair<String, String>> = arrayListOf()
                data.data.forEach {
                    DataToSend.add(Pair(it.title, it.url))
                }
                callback.onSuccess(DataToSend)

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                callback.onError(t.message!!)
            }


        })

    }

    fun getCoin(apiCallback: ApiCallback<List<TopCoin.Data>>) {
        ApiService.getTopCoin().enqueue(object : Callback<TopCoin> {
            override fun onResponse(call: Call<TopCoin>, response: Response<TopCoin>) {

                val data = response.body()!!
                apiCallback.onSuccess(data.data)

            }

            override fun onFailure(call: Call<TopCoin>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }

        })

    }

    fun getChart(
        fsym: String, period: String,
        apiCallback: ApiCallback<Pair<List<Chart.Data>, Chart.Data?>>,

        ) {
        var hisPeriod = ""
        var limit = 30
        var aggregate = 1

        when (period) {
            HOUR -> {
                hisPeriod = HISTO_MINUTE
                limit = 60
                aggregate = 12
            }

            HOURS24 -> {
                hisPeriod = HISTO_HOUR
                limit = 24
            }

            WEEK -> {
                hisPeriod = HISTO_HOUR
                aggregate = 6
            }

            MONTH -> {
                hisPeriod = HISTO_DAY
                limit = 30
            }

            MONTH3 -> {
                hisPeriod = HISTO_DAY
                limit = 90
            }

            YEAR -> {
                hisPeriod = HISTO_DAY
                aggregate = 13
            }

            ALL -> {
                hisPeriod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }
        }
        ApiService.getChart(hisPeriod, fsym, limit, aggregate).enqueue(object : Callback<Chart> {
            override fun onResponse(call: Call<Chart>, response: Response<Chart>) {
                val fullData = response.body()!!
                val data1 = fullData.data
                val data2 = fullData.data.maxByOrNull { it.close.toFloat() }
                val returnData = Pair(data1, data2)
                apiCallback.onSuccess(returnData)
            }

            override fun onFailure(call: Call<Chart>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }

        })

    }

    interface ApiCallback<T> {
        fun onSuccess(datas: T)
        fun onError(errorManager: String)
    }

}
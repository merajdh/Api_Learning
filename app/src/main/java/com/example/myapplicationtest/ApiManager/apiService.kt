package com.example.myapplicationtest.ApiManager

import com.example.myapplicationtest.Model.Chart
import com.example.myapplicationtest.Model.News
import com.example.myapplicationtest.Model.TopCoin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface apiService {
    @Headers (API_KEY)
    @GET ("v2/news/")
    fun getTopNews(
        @Query("sortOrder") sortOrder : String = "popular"
    ):Call<News>

    @Headers(API_KEY)
    @GET("top/totalvolfull")
    fun getTopCoin(
        @Query("tsym") to_symbol : String = "USD",
        @Query("limit") limit_data : Int = 10
    ):Call<TopCoin>


    @Headers(API_KEY)
    @GET("{period}")
    fun getChart(
        @Path("period") period : String ,
        @Query("fsym")fromSym : String ,
        @Query("limit")limit : Int ,
        @Query("aggregate") aggregate : Int,
        @Query("tsym")toSym : String = "USD" ,
        ) :Call<Chart>

}
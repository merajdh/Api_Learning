package com.example.myapplicationtest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationtest.ApiManager.ApiManager
import com.example.myapplicationtest.databinding.FragmentMarketBinding

class MainActivityMarket : AppCompatActivity() {
    lateinit var binding: FragmentMarketBinding
    lateinit var dataNews : ArrayList< Pair < String , String> >
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apimanager = ApiManager()
        InitUi()

    }

    private fun InitUi() {

        getNews()

    }

    private fun getNews() {
        val apimanager = ApiManager()


        apimanager.getNews(object :ApiManager.ApiCallback <ArrayList<Pair<String,String>>>{
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
                dataNews = data
                refreshNews()

            }

            override fun onError(errorManager: String) {
            }

        })

    }

    private fun refreshNews() {

        val randomAccess = (0..49).random()
        binding.mouduleOneMarket.txtNews.text = dataNews[randomAccess].first
        binding.mouduleOneMarket.txtNews.setOnClickListener{
            refreshNews()
        }
        binding.mouduleOneMarket.btnNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(dataNews[randomAccess].second))
            startActivity(intent)
        }

    }
}
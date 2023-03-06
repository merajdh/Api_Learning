package com.example.myapplicationtest.Ui.market

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.ApiManager.ApiManager
import com.example.myapplicationtest.Model.About
import com.example.myapplicationtest.Model.ItemAbout
import com.example.myapplicationtest.Model.TopCoin
import com.example.myapplicationtest.Ui.Coin.Coin_Activity
import com.example.myapplicationtest.databinding.ActivityMarketBinding
import com.google.gson.Gson

class MainActivityMarket : AppCompatActivity() , MareketAdapter.onClick{
    lateinit var binding: ActivityMarketBinding
    val apimanager = ApiManager()
    lateinit var dataNews : ArrayList< Pair < String , String> >
    lateinit var  aboutMap : MutableMap<String , ItemAbout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.refresh.setOnRefreshListener {

            InitUi()

            Handler (Looper.getMainLooper()).postDelayed({
                binding.refresh.isRefreshing = false

            },1000)
        }

    }

    override fun onResume() {
        super.onResume()
        InitUi()

    }

    private fun InitUi() {

        getAboutData()
        getNews()
        getTopCoins()

    }

    private fun getAboutData() {

        val dataString = applicationContext.assets.open("currencyinfo.json").bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val dataAll = gson.fromJson(dataString , About::class.java)

         aboutMap = mutableMapOf<String , ItemAbout>()

        dataAll.forEach{
            aboutMap[it.currencyName] = ItemAbout(
                it.info.web,
                it.info.twt,
                it.info.reddit,
                it.info.desc,
                it.info.github,
            )
        }
    }


    private fun getNews() {

        apimanager.getNews(object :ApiManager.ApiCallback <ArrayList<Pair<String,String>>>{
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
                dataNews = data
                refreshNews()

            }

            override fun onError(errorManager: String) {
                Toast.makeText(this@MainActivityMarket, "error is -> " + errorManager, Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun refreshNews() {

        val randomAccess = (0..49).random()
        binding.mouduleOneMarket.txtNews.text = dataNews[randomAccess].first
        Log.v("teest" , dataNews[randomAccess].first.toString())
        binding.mouduleOneMarket.txtNews.setOnClickListener{
            val anim = AlphaAnimation(0f , 2f )
            anim.duration = 1200
            anim.fillAfter = true
            binding.mouduleOneMarket.txtNews.startAnimation(anim)
            refreshNews()
        }
        binding.mouduleOneMarket.btnNews.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(dataNews[randomAccess].second))
            Log.v("teste" , dataNews[randomAccess].second)
            startActivity(intent)
        }

    }

    private fun getTopCoins() {


        apimanager.getCoin( object : ApiManager.ApiCallback<List<TopCoin.Data>>{
            override fun onSuccess(datas: List<TopCoin.Data>) {
                showDataRec(datas)
            }

            override fun onError(errorManager: String) {
                Toast.makeText(this@MainActivityMarket, "error" + errorManager , Toast.LENGTH_SHORT).show()
                Log.v("00" , errorManager)
            }

        })
    }


    private fun showDataRec(data : List<TopCoin.Data>){

        val  marketAdapter =MareketAdapter(ArrayList(data)  , this )
        binding.mouduleTwoMarket.recMain.adapter = marketAdapter
        binding.mouduleTwoMarket.recMain.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)

        binding.mouduleTwoMarket.btnMore.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW , Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }
    }

    override fun onItemClick(dataCoin: TopCoin.Data) {
        val intent = Intent(this@MainActivityMarket , Coin_Activity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("bundle1",dataCoin)
        bundle.putParcelable("bundle2", aboutMap[dataCoin.coinInfo.name])

        intent.putExtra("bundle" , bundle)
        startActivity(intent)
    }

}
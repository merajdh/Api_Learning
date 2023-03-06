package com.example.myapplicationtest.Ui.Coin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.myapplicationtest.ApiManager.*
import com.example.myapplicationtest.Model.Chart
import com.example.myapplicationtest.Model.ItemAbout
import com.example.myapplicationtest.Model.TopCoin
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.ActivityCoin2Binding

class Coin_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCoin2Binding
    lateinit var thisCoin : TopCoin.Data
    lateinit var dataAbout : ItemAbout
    val apiManager = ApiManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("bundle")!!
        if(bundle.getParcelable<ItemAbout>("bundle2") != null){
            dataAbout = bundle.getParcelable<ItemAbout>("bundle2")!!
        }
        thisCoin = bundle.getParcelable<TopCoin.Data>("bundle1")!!


        binding.mouduleToolbar.toolBar.title = thisCoin.coinInfo.fullName

        initUi()
    }

    private fun initUi() {

        initChartUi()

        initStatisticUi()

        initAboutUi()

    }


     fun initChartUi() {
         var period : String = HOUR
         requastData(period)
         binding.mouduleOneCoin.radioGroup.setOnCheckedChangeListener { _, checkedId ->

             when(checkedId){
                 R.id.radio12H -> {period = HOUR}
                 R.id.radio1D -> {period = HOURS24}
                 R.id.radio1W -> {period = WEEK}
                 R.id.radio1m -> {period = MONTH}
                 R.id.radio3m -> {period = MONTH3}
                 R.id.radio1Y -> {period = YEAR}
                 R.id.radioAll -> {period = ALL}
             }
             requastData(period)

         }

         binding.mouduleOneCoin.txtPrice.text = thisCoin.dISPLAY.uSD.pRICE
         binding.mouduleOneCoin.txtChange1.text = thisCoin.dISPLAY.uSD.cHANGE24HOUR
         binding.mouduleOneCoin.txtChange2.text = thisCoin.dISPLAY.uSD.cHANGEDAY

         val change = thisCoin.rAW.uSD.cHANGE24HOUR

         if (change < 0){
             binding.mouduleOneCoin.txtChange1.setTextColor(ContextCompat.getColor(binding.root.context , R.color.red ))

         }else if (change > 0) {
             binding.mouduleOneCoin.txtChange1.setTextColor(ContextCompat.getColor(binding.root.context , R.color.green ))

         }else{
             binding.mouduleOneCoin.txtChange1.setTextColor(ContextCompat.getColor(binding.root.context , R.color.gray ))
         }




     }
    @SuppressLint("SetTextI18n")
    private fun initStatisticUi() {
        binding.mouduleTwoCoin.txtOpen.text = thisCoin.dISPLAY.uSD.oPEN24HOUR
        binding.mouduleTwoCoin.txtHighToday.text = thisCoin.dISPLAY.uSD.hIGH24HOUR
        binding.mouduleTwoCoin.txtLowToday.text = thisCoin.dISPLAY.uSD.lOW24HOUR
        binding.mouduleTwoCoin.txtChangeToday.text =  thisCoin.dISPLAY.uSD.cHANGE24HOUR
        binding.mouduleTwoCoin.txtVol.text = thisCoin.dISPLAY.uSD.vOLUME24HOUR
        binding.mouduleTwoCoin.txtTotalVol.text = thisCoin.dISPLAY.uSD.tOTALVOLUME24H
        binding.mouduleTwoCoin.txtMarketCap.text = thisCoin.dISPLAY.uSD.mKTCAP
        binding.mouduleTwoCoin.txtSupply.text = thisCoin.dISPLAY.uSD.sUPPLY
    }

     fun initAboutUi() {

        binding.ouduleThreeCoin.txtWeb.text = dataAbout.Web
        binding.ouduleThreeCoin.txtGithub.text = dataAbout.gitHub
        binding.ouduleThreeCoin.txtReddit.text = dataAbout.Reddit
        binding.ouduleThreeCoin.txtTwitter.text = dataAbout.Twt
        binding.ouduleThreeCoin.txtDesc.text = dataAbout.Desc

         binding.ouduleThreeCoin.txtWeb.setOnClickListener {
             openWebsite(dataAbout.Web!!)
         }

         binding.ouduleThreeCoin.txtReddit.setOnClickListener {
             openWebsite(dataAbout.Reddit!!)
         }

         binding.ouduleThreeCoin.txtGithub.setOnClickListener {
             openWebsite(dataAbout.gitHub!!)
         }

         binding.ouduleThreeCoin.txtTwitter.setOnClickListener {
             openWebsite("https://twitter.com/"  + dataAbout.Twt)
         }


    }

    private fun openWebsite(url:String){
        val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
        startActivity(intent)
    }

    fun requastData(period : String){
        apiManager.getChart(thisCoin.coinInfo.name , period ,object : ApiManager.ApiCallback<Pair<List<Chart.Data> , Chart.Data?>>{
            override fun onSuccess(datas: Pair<List<Chart.Data>, Chart.Data?>) {
                Log.v("test2" , datas.first.toString())
                val chartAdapter = ChartAdapter(datas.first , datas.second?.open.toString() )
                binding.mouduleOneCoin.sparkView.adapter = chartAdapter

            }

            override fun onError(errorManager: String) {
                Log.v("test2" , errorManager)
            }

        })

    }
}
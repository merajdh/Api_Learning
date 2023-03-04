package com.example.myapplicationtest.Ui.Coin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationtest.Model.ItemAbout
import com.example.myapplicationtest.Model.TopCoin
import com.example.myapplicationtest.databinding.ActivityCoin2Binding

class Coin_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCoin2Binding
    lateinit var thisCoin : TopCoin.Data
    lateinit var dataAbout : ItemAbout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("bundle")!!
        thisCoin = bundle.getParcelable<TopCoin.Data>("bundle1")!!

        dataAbout = bundle.getParcelable<ItemAbout>("bundle2")!!


        binding.mouduleToolbar.toolBar.title = thisCoin.coinInfo.fullName

        initUi()
    }

    private fun initUi() {

        initChartUi()

        initStatisticUi()

        initAboutUi()

    }


    private fun initChartUi() {
        
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
}
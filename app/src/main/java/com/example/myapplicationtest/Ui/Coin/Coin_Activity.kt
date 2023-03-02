package com.example.myapplicationtest.Ui.Coin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationtest.Model.TopCoin
import com.example.myapplicationtest.databinding.ActivityCoin2Binding

class Coin_Activity : AppCompatActivity() {
    lateinit var binding: ActivityCoin2Binding
    lateinit var thisCoin : TopCoin.Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        thisCoin = intent.getParcelableExtra<TopCoin.Data>("dataSend")!!

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

        binding.mouduleTwoCoin.txtOpen.text = thisCoin.dISPLAY.uSD.oPEN24HOUR.toString()
        binding.mouduleTwoCoin.txtHighToday.text = thisCoin.dISPLAY.uSD.hIGH24HOUR.toString()
        binding.mouduleTwoCoin.txtLowToday.text = thisCoin.dISPLAY.uSD.lOW24HOUR.toString()
        binding.mouduleTwoCoin.txtChangeToday.text =  thisCoin.dISPLAY.uSD.cHANGE24HOUR.toString()
        binding.mouduleTwoCoin.txtVol.text = thisCoin.dISPLAY.uSD.vOLUME24HOUR.toString()
        binding.mouduleTwoCoin.txtTotalVol.text = thisCoin.dISPLAY.uSD.tOTALVOLUME24H.toString()
        binding.mouduleTwoCoin.txtMarketCap.text = thisCoin.dISPLAY.uSD.mKTCAP.toString()
        binding.mouduleTwoCoin.txtSupply.text = thisCoin.dISPLAY.uSD.sUPPLY.toString()
    }


    private fun initAboutUi() {

    }
}
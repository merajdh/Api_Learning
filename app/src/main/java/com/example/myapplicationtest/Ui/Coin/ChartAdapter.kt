package com.example.myapplicationtest.Ui.Coin

import com.example.myapplicationtest.Model.Chart
import com.robinhood.spark.SparkAdapter

class ChartAdapter ( private val historicalData : List<Chart.Data> , private val baseLine : String?) : SparkAdapter()
{
    override fun getCount(): Int {
        return historicalData.size
    }

    override fun getItem(index: Int): Chart.Data {
        return historicalData[index]
    }

    override fun getY(index: Int): Float {
        return historicalData[index].close.toFloat()
    }

    override fun hasBaseLine(): Boolean {
        return true
    }

    override fun getBaseLine(): Float {
        return baseLine?.toFloat() ?:super.getBaseLine()
    }
}
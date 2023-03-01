package com.example.myapplicationtest.Ui.market

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationtest.ApiManager.BASE_URL
import com.example.myapplicationtest.Model.TopCoin
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.ItemRecMarketBinding
import com.squareup.picasso.Picasso

class MareketAdapter(private val  data : List<TopCoin.Data> , private val  OnClick : onClick) :  RecyclerView.Adapter<MareketAdapter.MarketViewHolder>(){
    lateinit var binding: ItemRecMarketBinding

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bindViews(dataCoin : TopCoin.Data){

            binding.txtName.text = dataCoin.coinInfo.name

            binding.txtPrice.text = "$" + dataCoin.rAW.uSD.pRICE.toString()


            val marketCap = dataCoin.rAW.uSD.mKTCAP / 1000000000
            val DotIndex = marketCap.toString().indexOf(".")

             binding.txtCap.text = "$" + dataCoin.rAW.uSD.mKTCAP.toString().substring(0 , DotIndex + 2 ) + " B"


            val change = dataCoin.rAW.uSD.cHANGE24HOUR

            if (change < 0){
                binding.txtChange.setTextColor(ContextCompat.getColor(binding.root.context , R.color.red ))
                binding.txtChange.text = "%" + dataCoin.rAW.uSD.cHANGE24HOUR.toString().substring(0  , 7)

            }else if (change > 0) {
                binding.txtChange.setTextColor(ContextCompat.getColor(binding.root.context , R.color.green ))
                binding.txtChange.text = "%" + dataCoin.rAW.uSD.cHANGE24HOUR.toString().substring(0  , 6)

            }else{
                binding.txtChange.text = "0%"
            }


            Glide.with(binding.root.context).load(BASE_URL + dataCoin.coinInfo.imageUrl) .into(binding.imageView)

            itemView.setOnClickListener {
                OnClick.onItemClick(dataCoin)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecMarketBinding.inflate(inflater , parent, false)

        return MarketViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface onClick {

        fun onItemClick (dataCoin: TopCoin.Data)
    }


}
package com.example.myapplicationtest.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplicationtest.ApiManager.ApiManager
import com.example.myapplicationtest.databinding.FragmentMarketBinding

class marketFragment : Fragment() {

    lateinit var binding:FragmentMarketBinding

    val apimanager = ApiManager()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMarketBinding.inflate(layoutInflater , container , false)

        InitUi()

        return binding.root
    }

    private fun InitUi() {

        getNews()

    }

    private fun getNews() {

        apimanager.getNews(object :ApiManager.ApiCallback <ArrayList<Pair<String,String>>>{
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
            }

            override fun onError(errorManager: String) {
                Toast.makeText(context, "error is = " + errorManager , Toast.LENGTH_SHORT).show()
            }

        })

    }
}
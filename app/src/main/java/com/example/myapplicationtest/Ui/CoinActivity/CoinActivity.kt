package com.example.myapplicationtest.Ui.CoinActivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationtest.databinding.FragmentCoinBinding

class CoinActivity : AppCompatActivity() {
    lateinit var binding: FragmentCoinBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}
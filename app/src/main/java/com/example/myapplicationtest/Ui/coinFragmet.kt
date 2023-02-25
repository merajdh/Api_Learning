package com.example.myapplicationtest.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplicationtest.databinding.FragmentCoinBinding

class coinFragmet : Fragment() {
    lateinit var binding: FragmentCoinBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoinBinding.inflate(layoutInflater , container ,false)



        return binding.root
    }

}
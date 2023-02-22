package com.example.myapplicationtest.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplicationtest.databinding.FragmentMarketBinding

class marketFragment : Fragment() {

    lateinit var binding:FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketBinding.inflate(layoutInflater , container , false)

        return binding.root
    }
}
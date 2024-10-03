package com.mistercoding.compak.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mistercoding.compak.R
import com.mistercoding.compak.databinding.FragmentHomeBinding
import com.mistercoding.compak.products.CarbonCalculator


class HomeFragment : Fragment() {


    private lateinit var binding : FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the button click listener
        binding.carbonButton.setOnClickListener {
            // Navigate to the second activity
            val intent = Intent(requireContext(), CarbonCalculator::class.java)
            startActivity(intent)
        }
        binding.carbonButtonn.setOnClickListener {
            // Navigate to the second activity
            val intent = Intent(requireContext(), CarbonCalculator::class.java)
            startActivity(intent)
        }

    }



}
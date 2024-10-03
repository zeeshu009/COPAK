package com.mistercoding.compak.products

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistercoding.compak.R
import com.mistercoding.compak.databinding.ActivityFlightsBinding

class Flights : AppCompatActivity() {
    private  lateinit var binding: ActivityFlightsBinding
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        binding = ActivityFlightsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Set up the spinners
        val airports = arrayOf("Select...", "JFK", "LAX", "LHR", "CDG")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, airports)
        binding.spinnerDeparture.adapter = adapter
        binding.spinnerArrival.adapter = adapter

        val cabinClasses = arrayOf("Economy", "Business", "First Class")
        val cabinClassAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cabinClasses)
        binding.spinnerCabinClass.adapter = cabinClassAdapter

        // Handle button click
        binding.btnCalculateCost.setOnClickListener {
            Toast.makeText(this, "Calculating cost...", Toast.LENGTH_SHORT).show()
        }

        // Add details click listener
        binding.tvAddDetails.setOnClickListener {
            Toast.makeText(this, "Adding more details...", Toast.LENGTH_SHORT).show()
        }

    }
}
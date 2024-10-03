package com.mistercoding.compak.products

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistercoding.compak.R

class PetActivity : AppCompatActivity() {
    private var catsCount = 0
    private var dogsCount = 0
    private var largeDogsCount = 0

    private lateinit var catsCountText: TextView
    private lateinit var dogsCountText: TextView
    private lateinit var largeDogsCountText: TextView
    private lateinit var carbonFootprintValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pet)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize views
        val petRadioGroup = findViewById<RadioGroup>(R.id.petRadioGroup)
        val yesButton = findViewById<RadioButton>(R.id.yesButton)
        catsCountText = findViewById(R.id.catsCount)
        dogsCountText = findViewById(R.id.dogsCount)
        largeDogsCountText = findViewById(R.id.largeDogsCount)

        val minusCatsButton = findViewById<Button>(R.id.minusCatsButton)
        val plusCatsButton = findViewById<Button>(R.id.plusCatsButton)
        val minusDogsButton = findViewById<Button>(R.id.minusDogsButton)
        val plusDogsButton = findViewById<Button>(R.id.plusDogsButton)
        val minusLargeDogsButton = findViewById<Button>(R.id.minusLargeDogsButton)
        val plusLargeDogsButton = findViewById<Button>(R.id.plusLargeDogsButton)
        val addPetButton = findViewById<Button>(R.id.doneButton)

        // Handle Yes/No RadioGroup selection
        petRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.yesButton -> {
                    Toast.makeText(this, "Yes selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handle Cat count increment/decrement
        minusCatsButton.setOnClickListener {
            if (catsCount > 0) catsCount--
            updateCounts()
        }

        plusCatsButton.setOnClickListener {
            catsCount++
            updateCounts()
        }

        // Handle Dog count increment/decrement
        minusDogsButton.setOnClickListener {
            if (dogsCount > 0) dogsCount--
            updateCounts()
        }

        plusDogsButton.setOnClickListener {
            dogsCount++
            updateCounts()
        }

        // Handle Large Dog count increment/decrement
        minusLargeDogsButton.setOnClickListener {
            if (largeDogsCount > 0) largeDogsCount--
            updateCounts()
        }

        plusLargeDogsButton.setOnClickListener {
            largeDogsCount++
            updateCounts()
        }

        // Handle Add Pet Button click
        addPetButton.setOnClickListener {
            if (catsCount == 0 && dogsCount == 0 && largeDogsCount == 0) {
                Toast.makeText(this, "Please add at least one pet", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pets added!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateCounts() {
        catsCountText.text = catsCount.toString()
        dogsCountText.text = dogsCount.toString()
        largeDogsCountText.text = largeDogsCount.toString()
        updateCarbonFootprint()
    }

    private fun updateCarbonFootprint() {
        // Logic to calculate and update the carbon footprint based on pet counts
        val baseCarbonFootprint = 22.5
        val additionalFootprint = catsCount * 0.5 + dogsCount * 1.0 + largeDogsCount * 1.5
        val totalCarbonFootprint = baseCarbonFootprint + additionalFootprint

        carbonFootprintValue.text = String.format("%.1f tons of CO₂ per year", totalCarbonFootprint)
    }
}


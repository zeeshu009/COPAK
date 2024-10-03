package com.mistercoding.compak.products

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.mistercoding.compak.R


class OffsetAnything : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_offset_anything)


        val supportLayout = findViewById<LinearLayout>(R.id.support_layout)
        val trailblazerLayout = findViewById<LinearLayout>(R.id.trailblazer_layout)
        val amountInput = findViewById<EditText>(R.id.amount_input)

        val selectRoadTrip = findViewById<Button>(R.id.select_road_trip)
        val offsetButton = findViewById<Button>(R.id.offset_button)

        val select_christmas_party = findViewById<Button>(R.id.select_christmas_party)
        val select_domestic_flight = findViewById<Button>(R.id.select_domestic_flight)
        val select_steak_dinner = findViewById<Button>(R.id.select_steak_dinner)




        supportLayout.setOnClickListener {

            val price  = amountInput.text.toString().toDouble()

            offsetButton.text = "Offset $price ton of CO₂ (${price*25.0})"



        }
        trailblazerLayout.setOnClickListener {

            val price  = amountInput.text.toString().toDouble()

            offsetButton.text = "Offset $price ton of CO₂ (${price*400.0})"

        }

        selectRoadTrip.setOnClickListener {
            amountInput.setText("0.5")
        }
        select_christmas_party.setOnClickListener {
            amountInput.setText("0.08")
        }
        select_domestic_flight.setOnClickListener {
            amountInput.setText("1")
        }
        select_steak_dinner.setOnClickListener {
            amountInput.setText("0.005")
        }









    }
}
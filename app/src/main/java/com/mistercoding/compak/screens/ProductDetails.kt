package com.mistercoding.compak.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistercoding.compak.classes.Products
import com.mistercoding.compak.R
import com.mistercoding.compak.products.Bussiness
import com.mistercoding.compak.products.CarbonCalculator
import com.mistercoding.compak.products.Flights
import com.mistercoding.compak.products.Fundraisers
import com.mistercoding.compak.products.Gifts
import com.mistercoding.compak.products.OffsetAnything
import com.mistercoding.compak.products.Subscription
import com.mistercoding.compak.products.WrenAction

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_product_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val product = intent.getParcelableExtra<Products>("products")

        if(product != null)
        {
            val img = findViewById<ImageView>(R.id.product_image)
            val title = findViewById<TextView>(R.id.product_text)
            val description = findViewById<TextView>(R.id.product_description)
            if(product.name=="Gifts")
            {
                val intent = Intent(this, Gifts::class.java)
                startActivity(intent)
                finish()
            }
            else if(product.name=="Carbon Calculator")
            {
                val intent = Intent(this, CarbonCalculator::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Business"){

                val intent = Intent(this,Bussiness::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Copak Subscription"){

                val intent = Intent(this, Subscription::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Flights"){

                val intent = Intent(this, Flights::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Fundraisers"){

                val intent = Intent(this, Fundraisers::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Copak Actions"){

                val intent = Intent(this, WrenAction::class.java)
                startActivity(intent)
                finish()
            }
            else if (product.name=="Offset anything"){

                val intent = Intent(this, OffsetAnything::class.java)
                startActivity(intent)
                finish()
            }

            else
            {
            img.setImageResource(product.image)
            title.text = product.name
            description.text = product.description
            }
        }
        else
        {
            Toast.makeText(this,"Product not found",Toast.LENGTH_SHORT).show()
        }
    }
}
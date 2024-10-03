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
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Products
import com.mistercoding.compak.classes.Resources
import com.mistercoding.compak.products.CarbonCalculator
import com.mistercoding.compak.products.Gifts
import com.mistercoding.compak.resources.AboutUs
import com.mistercoding.compak.resources.FAQs
import com.mistercoding.compak.resources.Offsets
import com.mistercoding.compak.resources.OurApproach
import com.mistercoding.compak.resources.OurProjects
import com.mistercoding.compak.resources.ProjectCertification
import com.mistercoding.compak.resources.ProjectUpdates

class ResourcesDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resources_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resource = intent.getParcelableExtra<Resources>("resource")

        if(resource != null)
        {
            val img = findViewById<ImageView>(R.id.resource_image)
            val title = findViewById<TextView>(R.id.resource_text)
            val description = findViewById<TextView>(R.id.resource_description)
            if(resource.name=="About us")
            {
                val intent = Intent(this, AboutUs::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="Our approach"){
                val intent = Intent(this, OurApproach::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="Project certification"){
                val intent = Intent(this, ProjectCertification::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="Our view on offsets"){
                val intent = Intent(this, Offsets::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="Project updates"){
                val intent = Intent(this, ProjectUpdates::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="FAQs"){
                val intent = Intent(this, FAQs::class.java)
                startActivity(intent)
                finish()
            }
            else if(resource.name=="Our projects"){
                val intent = Intent(this, OurProjects::class.java)
                startActivity(intent)
                finish()
            }
            else {
                img.setImageResource(resource.image)
                title.text = resource.name
                description.text = resource.description
            }
        }
        else
        {
            Toast.makeText(this,"Resource not found", Toast.LENGTH_SHORT).show()
        }
    }
}
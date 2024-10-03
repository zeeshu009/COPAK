package com.mistercoding.compak.resources

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.adapters.ProjectsAdapter
import com.mistercoding.compak.retrofit.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OurProjects : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_our_projects)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        GlobalScope.launch {
            try {
                // Assume RetrofitInstance.api.getAllProjects() returns a List<Projects>
                val projects = RetrofitInstance.api.getAllProjects()

                // Filter the projects with the solution "Technology"
                val technologyProjects = projects.filter { it.solution == "Technology" }
                val TreePlantingProjects = projects.filter { it.solution == "TreePlanting" }
                val PolicyProjects = projects.filter { it.solution == "Policy" }

               // Setup RecyclerView
              val technologyRecyclerView: RecyclerView = findViewById(R.id.TechnologyRecyclerView)
                val treeplantingRecyclerView: RecyclerView = findViewById(R.id.TreePlantingRecyclerView)
                val policyRecyclerView: RecyclerView = findViewById(R.id.PolicyRecyclerView)
              val adapter = ProjectsAdapter(technologyProjects)
                val adapter2 = ProjectsAdapter(TreePlantingProjects)
                val adapter3 = ProjectsAdapter(PolicyProjects)


                runOnUiThread {

                    technologyRecyclerView.adapter = adapter
                    treeplantingRecyclerView.adapter = adapter2
                    policyRecyclerView.adapter = adapter3
                    technologyRecyclerView.layoutManager = LinearLayoutManager(this@OurProjects)
                    treeplantingRecyclerView.layoutManager = LinearLayoutManager(this@OurProjects)
                    policyRecyclerView.layoutManager = LinearLayoutManager(this@OurProjects)
                }
            } catch (e: Exception) {
                runOnUiThread {

                }
            }
        }
    }
}
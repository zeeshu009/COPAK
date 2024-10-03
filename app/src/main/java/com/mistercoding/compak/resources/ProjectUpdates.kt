package com.mistercoding.compak.resources

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.adapters.ProjectsAdapter
import com.mistercoding.compak.adapters.UpdateAdapter
import com.mistercoding.compak.retrofit.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProjectUpdates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_project_updates)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        GlobalScope.launch {
            try {

                val updates = RetrofitInstance.api.getAllUpdates()
                // Setup RecyclerView
                val updatesRecyclerView: RecyclerView = findViewById(R.id.updatesRecyclerView)
                val adapter = UpdateAdapter(updates)
                runOnUiThread {
                    updatesRecyclerView.adapter = adapter
                    updatesRecyclerView.layoutManager = LinearLayoutManager(this@ProjectUpdates)
                }
            } catch (e: Exception) {
                runOnUiThread {

                }
            }
        }
    }
}
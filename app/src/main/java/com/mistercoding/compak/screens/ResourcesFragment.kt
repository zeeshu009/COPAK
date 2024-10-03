package com.mistercoding.compak.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.adapters.ProductAdapter

import com.mistercoding.compak.adapters.ResourceAdapter
import com.mistercoding.compak.classes.Products
import com.mistercoding.compak.classes.Resources
import com.mistercoding.compak.resources.AboutUs
import com.mistercoding.compak.resources.ProjectCertification


class ResourcesFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var resourceList:ArrayList<Resources>
    private lateinit var resourceAdapter: ResourceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.r_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        resourceList = ArrayList()
        addDataToList()

        // now initialize our productAdapter class and passing resource list in it
        resourceAdapter = ResourceAdapter(resourceList)
        recyclerView.adapter = resourceAdapter

        resourceAdapter.onItemClick = {
            val intent = Intent(context,ResourcesDetails::class.java)
            intent.putExtra("resource",it)
            startActivity(intent)
        }
    }

    private fun addDataToList()
    {
        resourceList.add(Resources(R.drawable.leaf,"Our projects",  "What we're funding now"))
        resourceList.add(Resources(R.drawable.hummingbird,"About us", "Our team and backstory"))
        resourceList.add(Resources(R.drawable.teamwork,"Project updates", "The latest from our projects"))
        resourceList.add(Resources(R.drawable.carbonoffsets,"Our view on offsets", "The do's and dont's of offsets"))
        resourceList.add(Resources(R.drawable.teamwork,"Our approach", "How we think about Wren"))
        resourceList.add(Resources(R.drawable.donation,"Project certification", "How Wren thinks about impact"))
        resourceList.add(Resources(R.drawable.circle_question_mark,"FAQs", "Your Questions , answers"))

    }

}
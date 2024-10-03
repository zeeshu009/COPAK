package com.mistercoding.compak.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.adapters.ProductAdapter
import com.mistercoding.compak.classes.Products
import com.mistercoding.compak.R




class ProductsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productList:ArrayList<Products>
    private lateinit var productAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.p_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        productList = ArrayList()
        addDataToList()

        // now initialize our productAdapter class and passing productlist in it
        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        productAdapter.onItemClick = {
            val intent = Intent(context,ProductDetails::class.java)
            intent.putExtra("products",it)
            startActivity(intent)
        }
    }

    private fun addDataToList()
    {
        productList.add(Products(R.drawable.hummingbird,"Copak Subscription","Recurrently fund climate projects"))
        productList.add(Products(R.drawable.calculator,"Carbon Calculator","Understand your carbon footprint"))
        productList.add(Products(R.drawable.teamwork,"Business","Explore copak business offerings"))
        productList.add(Products(R.drawable.carbonoffsets,"Offset anything","Offset any amount of carbon"))
        productList.add(Products(R.drawable.leaf,"Copak Actions","Explore copak actions"))
        productList.add(Products(R.drawable.plane,"Flights","Log flights and offset them"))
        productList.add(Products(R.drawable.donation,"Fundraisers","Raise money for climate projects"))
        productList.add(Products(R.drawable.gift,"Gifts","Gifts climate impact to loved ones"))

    }
}
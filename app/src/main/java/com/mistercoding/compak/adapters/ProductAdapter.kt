package com.mistercoding.compak.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Products

class ProductAdapter(private val productList: ArrayList<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    // in this class we have to find all widget id from layout which we select
    // for recycler view
    var onItemClick : ((Products) -> Unit) ? = null
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage = itemView.findViewById<ImageView>(R.id.p_image)
        val productName = itemView.findViewById<TextView>(R.id.p_title)
        val productDescription = itemView.findViewById<TextView>(R.id.p_description)

    }
    // in this method we have to return layout view like item layout in our project
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    // we have to set data of every item in this class
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val products = productList[position]
        // with the help of holder we have to set each item like image and text in this
        // project
        holder.productImage.setImageResource(products.image)
        holder.productName.text = products.name
        holder.productDescription.text = products.description

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(products)
        }
    }
}
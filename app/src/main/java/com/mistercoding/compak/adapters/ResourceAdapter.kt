package com.mistercoding.compak.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Products
import com.mistercoding.compak.classes.Resources

class ResourceAdapter(private val resoucetList: ArrayList<Resources>) :
    RecyclerView.Adapter<ResourceAdapter.ResourceAdapter>() {
    // in this class we have to find all widget id from layout which we select
    // for recycler view
    var onItemClick : ((Resources) -> Unit) ? = null
    class ResourceAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val resourceImage = itemView.findViewById<ImageView>(R.id.r_image)
        val resourceName = itemView.findViewById<TextView>(R.id.r_title)
        val resourceDescription = itemView.findViewById<TextView>(R.id.r_description)

    }
    // in this method we have to return layout view like item layout in our project
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resource_item,parent,false)
        return ResourceAdapter(view)
    }

    override fun getItemCount(): Int {
        return resoucetList.size
    }
    // we have to set data of every item in this class
    override fun onBindViewHolder(holder: ResourceAdapter, position: Int) {
        val resources = resoucetList[position]
        // with the help of holder we have to set each item like image and text in this
        // project
        holder.resourceImage.setImageResource(resources.image)
        holder.resourceName.text = resources.name
        holder.resourceDescription.text = resources.description

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(resources)
        }
    }
}
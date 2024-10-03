package com.mistercoding.compak.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Update
import com.squareup.picasso.Picasso

class UpdateAdapter(private val updateList: List<Update>) :
    RecyclerView.Adapter<UpdateAdapter.UpdateViewHolder>() {

    class UpdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val typeTextView: TextView = itemView.findViewById(R.id.solution)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_update_view, parent, false)
        return UpdateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UpdateViewHolder, position: Int) {
        val update = updateList[position]

        holder.titleTextView.text = update.title
        holder.typeTextView.text = update.solutionType

        // Load image if URL is available
        val imageResource = holder.itemView.context.resources.getIdentifier(
            update.image,
            "drawable",
            holder.itemView.context.packageName
        )
        if (imageResource != 0) {
            holder.imageView.setImageResource(imageResource)
        } else {
            holder.imageView.setImageResource(R.drawable.cleancookingfuelimage1) // Fallback image
        }
    }

    override fun getItemCount(): Int = updateList.size
}

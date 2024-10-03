package com.mistercoding.compak.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Projects

class ProjectsAdapter(private val projectList: List<Projects>) :
    RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val projectImageView: ImageView = itemView.findViewById(R.id.projectImageView)
        val benefitsContainer: LinearLayout = itemView.findViewById(R.id.benefitsContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.our_projects, parent, false)
        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projectList[position]

        holder.titleTextView.text = project.title
        holder.descriptionTextView.text = project.description

        // Convert drawable name to resource ID
        val resourceId = holder.itemView.context.resources.getIdentifier(
            project.imageUrl,
            "drawable",
            holder.itemView.context.packageName
        )

        if (resourceId != 0) {
            holder.projectImageView.setImageResource(resourceId)
        } else {
            Log.e("ProjectsAdapter", "Resource not found for imageUrl: ${project.imageUrl}")
            holder.projectImageView.setImageResource(R.drawable.cleancookingfuelimage1) // Fallback image
        }

        // Clear any previous benefits before adding new ones
        holder.benefitsContainer.removeAllViews()

        // Split the benefits and dynamically add them to the container
        val benefits = project.benefits.split(",")
        benefits.forEach { benefit ->
            val benefitView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.item_benefit, holder.benefitsContainer, false)

            val benefitText: TextView = benefitView.findViewById(R.id.benefitText)
            benefitText.text = benefit.trim()

            holder.benefitsContainer.addView(benefitView)
        }
    }

    override fun getItemCount(): Int = projectList.size
}

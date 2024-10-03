package com.mistercoding.compak.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mistercoding.compak.R
import com.mistercoding.compak.classes.Plans

class PlanAdapter(private val plans: List<Plans>) : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    class PlanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val type: TextView = view.findViewById(R.id.type)
        val description: LinearLayout = view.findViewById(R.id.linearLayoutDetails)
        val chooseButton: Button = view.findViewById(R.id.btnChoosePlan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = plans[position]
        holder.name.text = plan.name
        holder.price.text = "$${plan.price}"
        holder.type.text = plan.type

        // Split the description by comma and add each part as a separate TextView
        val details = plan.description.split(",")
        holder.description.removeAllViews()
        details.forEach { detail ->
            val detailTextView = TextView(holder.itemView.context)
            detailTextView.text = "✔ $detail"
            detailTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
            holder.description.addView(detailTextView)
        }

    }

    override fun getItemCount(): Int {
        return plans.size
    }
}
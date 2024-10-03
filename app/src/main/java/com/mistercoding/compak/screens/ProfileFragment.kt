package com.mistercoding.compak.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.mistercoding.compak.R
import com.mistercoding.compak.adapters.PlanAdapter
import com.mistercoding.compak.authentication.ActivitySignIn
import com.mistercoding.compak.products.CarbonCalculator
import com.mistercoding.compak.retrofit.ApiService
import com.mistercoding.compak.retrofit.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.plans_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val email: TextView = view.findViewById(R.id.profile_email)
        val logoutButton: Button = view.findViewById(R.id.logout_button)
        val carbonCalculatorButton: Button = view.findViewById(R.id.carbon_calculator_button)

        FirebaseAuth.getInstance().currentUser?.email?.let { userEmail ->
            email.text = userEmail
            lifecycleScope.launch {
                try {
                    val plans = RetrofitInstance.api.getUserSubscriptions(userEmail)
                    withContext(Dispatchers.Main) {
                        adapter = PlanAdapter(plans)
                        recyclerView.adapter = adapter
                    }
                } catch (e: Exception) {
                    adapter = PlanAdapter(emptyList())
                    recyclerView.adapter = adapter
                }
            }

        } ?: run {
            email.text = "No user logged in"
        }

        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, ActivitySignIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        carbonCalculatorButton.setOnClickListener {
            val intent = Intent(requireContext(), CarbonCalculator::class.java)
            startActivity(intent)
        }
    }

}

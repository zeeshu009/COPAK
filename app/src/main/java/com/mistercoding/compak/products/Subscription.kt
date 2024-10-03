package com.mistercoding.compak.products

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.mistercoding.compak.R
import com.mistercoding.compak.adapters.PlanAdapter
import com.mistercoding.compak.databinding.ActivitySubscriptionBinding
import com.mistercoding.compak.retrofit.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Subscription : AppCompatActivity() {
    private lateinit var binding: ActivitySubscriptionBinding
    private val CHANNEL_ID = "Copak"
    private val Channel_Name = "Compak"
    private val Channel_Descripiton = "Climate positive"
    private val Notification_ID = 0
    private val REQUEST_NOTIFICATION_PERMISSION = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationChannel()


        val score = intent.getDoubleExtra("score",0.0).toFloat()

        val barChart = findViewById<BarChart>(R.id.barChart)

        // Hardcoded values for different countries
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 6.7f))  // U.K.
        entries.add(BarEntry(1f, 18.3f)) // United States
        entries.add(BarEntry(2f, score)) // You (Your score)
        entries.add(BarEntry(3f, 2.4f))  // India
        entries.add(BarEntry(4f, 4.9f))  // World

        // Setting up the dataset
        val dataSet = BarDataSet(entries, "Your Carbon FootPrint here")
        dataSet.colors = listOf(
            Color.parseColor("#F2C94C"), // U.K.
            Color.parseColor("#F2994A"), // United States
            Color.parseColor("#F2994A"), // You
            Color.parseColor("#F2C94C"), // India
            Color.parseColor("#F2C94C")  // World
        )
        // Creating BarData and setting it to the chart
        val barData = BarData(dataSet)
        barData.barWidth = 0.9f  // Bar width
        barChart.data = barData

        // Customizing chart appearance
        barChart.description.isEnabled = false
        barChart.setDrawGridBackground(false)
        barChart.setFitBars(true)  // Make the bars fit nicely in the chart view
        barChart.animateY(1000)

        // X-axis customization
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = entries.size
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("U.K.", "United States", "You", "India", "World"))

        // Y-axis customization (Optional)
        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f  // Start from 0

        val rightAxis = barChart.axisRight
        rightAxis.isEnabled = false  // Disable right axis

        barChart.invalidate()  //

        GlobalScope.launch {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission if not granted
                requestNotificationPermission()
            }
            val notification = Notification.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle("Copak")
                .setContentText("Climate steward plan selected")
                .setSmallIcon(R.drawable.baseline_attach_money_24)
                .build()

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(Notification_ID, notification)
            }
            try {
                // Assume RetrofitInstance.api.getAllProjects() returns a List<Projects>
                val plans = RetrofitInstance.api.getAllPlans()
                val monthlyPlans = plans.filter { it.type == "Monthly" }
                val selectedPlanId = monthlyPlans.firstOrNull()?.id ?: -1
                if (selectedPlanId != -1) {
                    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
                    RetrofitInstance.api.subscribePlan(userEmail, selectedPlanId)
                }
                // Setup RecyclerView
                val monthlyRecyclerView: RecyclerView = findViewById(R.id.planRecyclerView)
                val adapter = PlanAdapter(monthlyPlans)
                runOnUiThread {
                    monthlyRecyclerView.adapter = adapter
                    monthlyRecyclerView.layoutManager = LinearLayoutManager(this@Subscription)
                }

            } catch (e: Exception) {
                runOnUiThread {

                }
            }
        }




        binding.rbMonthly.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission if not granted
                requestNotificationPermission()
                return@setOnClickListener
            }

            val notification = Notification.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle("Copak")
                .setContentText("Climate positive plan selected")
                .setSmallIcon(R.drawable.baseline_attach_money_24)
                .build()

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(Notification_ID, notification)
            }

            GlobalScope.launch {
                try {
                    // Assume RetrofitInstance.api.getAllProjects() returns a List<Projects>
                    val plans = RetrofitInstance.api.getAllPlans()
                    val monthlyPlans = plans.filter { it.type == "Monthly" }
                    val selectedPlanId = monthlyPlans.firstOrNull()?.id ?: -1
                    if (selectedPlanId != -1) {
                        val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
                        RetrofitInstance.api.subscribePlan(userEmail, selectedPlanId)
                    }
                    // Setup RecyclerView
                    val monthlyRecyclerView: RecyclerView = findViewById(R.id.planRecyclerView)
                    val adapter = PlanAdapter(monthlyPlans)
                    runOnUiThread {
                        monthlyRecyclerView.adapter = adapter
                        monthlyRecyclerView.layoutManager = LinearLayoutManager(this@Subscription)
                    }
                } catch (e: Exception) {
                    runOnUiThread {

                    }
                }
            }

        }

        binding.rbYearly.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission if not granted
                requestNotificationPermission()
                return@setOnClickListener
            }

            val notification = Notification.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle("Copak")
                .setContentText("Climate steward plan selected")
                .setSmallIcon(R.drawable.baseline_attach_money_24)
                .build()

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(Notification_ID, notification)
            }
            GlobalScope.launch {
                try {
                    // Assume RetrofitInstance.api.getAllProjects() returns a List<Projects>
                    val plans = RetrofitInstance.api.getAllPlans()
                    val yearlyPlans = plans.filter { it.type == "Yearly" }
                    val selectedPlanId = yearlyPlans.firstOrNull()?.id ?: -1
                    if (selectedPlanId != -1) {
                        val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""
                        RetrofitInstance.api.subscribePlan(userEmail, selectedPlanId)
                    }
                    // Setup RecyclerView
                    val yearlyRecyclerView: RecyclerView = findViewById(R.id.planRecyclerView)
                    val adapter = PlanAdapter(yearlyPlans)
                    runOnUiThread {
                        yearlyRecyclerView.adapter = adapter
                        yearlyRecyclerView.layoutManager = LinearLayoutManager(this@Subscription)
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                    }
                }
            }
        }

        // Handle radio button change
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbMonthly -> {
                    Toast.makeText(this, "Monthly selected", Toast.LENGTH_SHORT).show()
                }
                R.id.rbYearly -> {
                    Toast.makeText(this, "Yearly selected", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun notificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = Channel_Name
            val descriptionText = Channel_Descripiton
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            }
        }
    }
}
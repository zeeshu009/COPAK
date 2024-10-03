package com.mistercoding.compak.screens


import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.mistercoding.compak.R
import com.mistercoding.compak.databinding.ActivityMainBinding
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{


    private lateinit var binding : ActivityMainBinding
    private var actionBarDrawerToggle : ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //toolbar icon click code
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        //setting navigation drawer
        setSupportActionBar(binding.toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()


        binding.navigationDrawer.setNavigationItemSelectedListener(this)


        //setting bottom navigation

        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(1,"Home",R.drawable.baseline_add_home_24)
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(2,"Products",R.drawable.baseline_production_quantity_limits_24)
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(3,"Resources",R.drawable.baseline_interests_24)
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(4,"Profile",R.drawable.baseline_manage_accounts_24)
        )



        binding.bottomNavigation.setOnClickMenuListener {
            when(it.id)
            {
                1 -> replaceFragment(HomeFragment())
                2 -> replaceFragment(ProductsFragment())
                3 -> replaceFragment(ResourcesFragment())
                4 -> replaceFragment(ProfileFragment())
            }

        }



        //set First Fragment
        replaceFragment(HomeFragment())
        binding.bottomNavigation.show(1)




    }

    private  fun replaceFragment(fragment:Fragment){

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_products -> replaceFragment(ProductsFragment())
            R.id.nav_resources -> replaceFragment(ResourcesFragment())
            R.id.nav_business -> replaceFragment(BusinessFragment())

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("This method has been deprecated")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.close()
        else
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle!!.onOptionsItemSelected(item))
            return true
        else
        return super.onOptionsItemSelected(item)
    }



}
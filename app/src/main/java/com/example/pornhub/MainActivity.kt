package com.example.pornhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pornhub.Adapter.ViewPagerAdapter
import com.example.pornhub.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Tablayout()
    }

    private fun Tablayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(UserFragment(),"User")

        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.user)
    }
}
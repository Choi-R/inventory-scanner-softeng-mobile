package com.example.inventoryscanner.qrscanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryscanner.qrscanner.fragments.HomeFragment
import com.example.inventoryscanner.qrscanner.fragments.ListFragment
import com.example.inventoryscanner.qrscanner.fragments.ScannerFragment
import com.example.inventoryscanner.qrscanner.fragments.adapters.ViewPagerAdapter
import com.example.inventoryscanner.R
import com.example.inventoryscanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()
    }
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"")
        adapter.addFragment(ScannerFragment(),"")
        adapter.addFragment(ListFragment(),"")
        binding.viewPager.adapter=adapter

        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_document_scanner_24)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_view_list_24)
    }
}
package com.example.inventoryscanner.qrscanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryscanner.qrscanner.fragments.HomeFragment
import com.example.inventoryscanner.qrscanner.fragments.ListFragment
import com.example.inventoryscanner.qrscanner.fragments.ScannerFragment
import com.example.inventoryscanner.qrscanner.fragments.adapters.ViewPagerAdapter
import com.example.inventoryscanner.R
import com.example.inventoryscanner.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ChangeTab {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnInsertData: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()

        btnInsertData = findViewById(R.id.btnInsertData)
        btnInsertData.setOnClickListener{
            startActivity(Intent(this, InsertionActivity::class.java))
        }
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

    override fun changeTab() {
        var currentItem = getItem(+1)
        viewPager.currentItem=currentItem

    }
    fun getItem(i:Int)= viewPager.currentItem +i
}
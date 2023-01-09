package com.example.inventoryscanner.qrscanner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.inventoryscanner.qrscanner.fragments.HomeFragment
import com.example.inventoryscanner.qrscanner.fragments.ListFragment
import com.example.inventoryscanner.qrscanner.fragments.ScannerFragment

class ViewPagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int =3


    override fun getItem(position: Int): Fragment {
        var getFragment: Fragment?=null
        when(position){
            0->getFragment = HomeFragment()
            1->getFragment=ScannerFragment()
            2->getFragment=ListFragment()
        }
        return getFragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when(position){
            0->title = "Tab-1"
            1->title = "Tab-2"
            2->title = "Tab-3"
        }
        return title
    }
}
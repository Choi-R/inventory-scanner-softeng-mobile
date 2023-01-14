package com.example.inventoryscanner.qrscanner.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.inventoryscanner.R
import com.example.inventoryscanner.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    var btnLogout: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogout= getView()?.findViewById<Button>(R.id.btnLogout)
        btnLogout!!.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this.context, LoginActivity::class.java)
            startActivity(intent)

        }
    }


}
package com.example.inventoryscanner.qrscanner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.inventoryscanner.R
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { key, bundle ->
            // Any type can be passed via to the bundle
            val result = bundle.getString("data")
            objId.text =result
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

   /* fun displayReceivedData(msg: String?){
        objId.text = "lalalal$msg"
        Toast.makeText(this.context,"powinno wyswietlic", Toast.LENGTH_SHORT).show()

    }
    */
}
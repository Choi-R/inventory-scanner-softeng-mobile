package com.example.inventoryscanner.qrscanner.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.inventoryscanner.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance("https://inventory-scanner-a8e6a-default-rtdb.europe-west1.firebasedatabase.app").getReference("items")

        setFragmentResultListener("requestKey") { key, bundle ->
            // Any type can be passed via to the bundle
            val result = bundle.getString("data")
            database.child(result.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    Log.i("firebase", "Got value ${it.value}")
                    objId.text=result
                    objName.text=it.child("name").value.toString()
                    objRoom.text=it.child("room").value.toString()
                    objInfo.text=it.child("information").value.toString()
                }
                else{
                    Toast.makeText(this.context,"Item doesn't exist",Toast.LENGTH_SHORT).show()
                    objId.text=result
                }
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
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
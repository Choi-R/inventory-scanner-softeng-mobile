package com.example.inventoryscanner.qrscanner

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryscanner.R
import com.example.inventoryscanner.data.model.ItemModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InsertionActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etRoom: EditText
    private lateinit var etInformation: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
//        auth = Firebase.auth

        btnSaveData = findViewById<Button>(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Items")
        btnSaveData.setOnClickListener{
            saveItemData()
        }
    }

    private fun saveItemData() {
        //getting values
        etName = findViewById<EditText>(R.id.etName)
        etRoom = findViewById<EditText>(R.id.etRoom)
        etInformation = findViewById<EditText>(R.id.etInformation)

        val name = etName.text.toString()
        val room = etRoom.text.toString()
        val information = etInformation.text.toString()

        if (name.isEmpty()) {
            etName.error = "Please enter name"
            return
        }
        if (room.isEmpty()) {
            etRoom.error = "Please enter room"
            return
        }
        if (information.isEmpty()) {
            etInformation.error = "Please enter information"
            return
        }
        val empId = dbRef.push().key!!
        val item = ItemModel(empId, name, room, information)

//        database = Firebase.database.reference
//        database.child("items").child(empId).setValue(item)
//            .addOnCompleteListener {
//                Log.i("Pertanda", "Masuk ke Complete")
//                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
//                etName.text.clear()
//                etRoom.text.clear()
//                etInformation.text.clear()
//            }.addOnFailureListener { err ->
//            Log.i("Error", "MASUK ERRORRRRRR")
//            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//            }

            etName.text.clear()
            etRoom.text.clear()
            etInformation.text.clear()
        Toast.makeText(this, "Not connected to database yet", Toast.LENGTH_LONG).show()

//        dbRef.child(empId).setValue(item)
//          .addOnCompleteListener {
//            Log.i("Pertanda", "Masuk ke Complete")
//            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
//            etName.text.clear()
//            etRoom.text.clear()
//            etInformation.text.clear()
//        }.addOnFailureListener { err ->
//            Log.i("Error", "MASUK ERRORRRRRR")
//            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//        }
    }
}
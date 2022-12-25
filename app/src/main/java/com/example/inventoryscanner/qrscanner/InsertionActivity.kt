package com.example.inventoryscanner.qrscanner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.inventoryscanner.R
import com.example.inventoryscanner.data.model.ItemModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etRoom: EditText
    private lateinit var etInformation: EditText
    private lateinit var btnSaveData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        btnSaveData = findViewById<Button>(R.id.btnSave)

        database = FirebaseDatabase.getInstance("https://inventory-scanner-a8e6a-default-rtdb.europe-west1.firebasedatabase.app").getReference("items")
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
        val empId = database.child("items").push().key!!;
        val item = ItemModel(empId, name, room, information)

        database.child(empId).setValue(item)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etRoom.text.clear()
                etInformation.text.clear()
            }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
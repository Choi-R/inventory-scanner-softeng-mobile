package com.example.inventoryscanner.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.inventoryscanner.QRscanner.MainActivity
import com.example.inventoryscanner.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        val logintext: TextView = findViewById(R.id.textView_login)

        logintext.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //email and password from user
        //:TODO VALIDATE DATA
        val registerButton: Button = findViewById(R.id.register)
        registerButton.setOnClickListener {
            performSingUp()
        }
    }
    private fun performSingUp(){
        val email = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        if(email.text.isEmpty()||password.text.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT)
                .show()
            return
        }


        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Success",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"Error occured${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }

    }
}
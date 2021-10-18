package com.example.coroutinecryptoapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coroutinecryptoapi.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth


class login : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        createAccountTextView.setOnClickListener {
            val intent = Intent(this,createUser::class.java)
            startActivity(intent)

        }
        loginButton.setOnClickListener {
            login()
        }

    }

    fun login(){
        val email:String = emailEditText.text.toString()
        val password:String = passwordTextView.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            task->
            if(task.isSuccessful){
                println("login is succesful")
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener{Exception ->
            Toast.makeText(applicationContext,"Login is Failed",Toast.LENGTH_LONG).show()
        }


    }
}
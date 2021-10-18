package com.example.coroutinecryptoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coroutinecryptoapi.R
import kotlinx.android.synthetic.main.activity_create_user.view.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class createUser : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        auth = FirebaseAuth.getInstance()

        createUserButton.setOnClickListener {
            createUserFun()
        }
    }

    fun createUserFun(){
        val email:String = EmailEditText.text.toString()
        val password:String = PasswordEditText.text.toString()
        val passwordAgain:String = PasswordEditText2.text.toString()

        if(password==passwordAgain){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Create User is Succcesfull", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener{Exception->
                Toast.makeText(applicationContext,"${Exception}",Toast.LENGTH_LONG).show()
            }

        }
        else
        {
            Toast.makeText(this,"Password dont Match",Toast.LENGTH_LONG).show()

        }
    }
}
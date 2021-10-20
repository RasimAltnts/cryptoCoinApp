package com.example.coroutinecryptoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutinecryptoapi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_forget_password.view.*

class forgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        sendForgetPasswordButton.setOnClickListener {
            forgetPassword()
        }
    }

    fun forgetPassword(){

        val auth = FirebaseAuth.getInstance()
        val currentEmail = forgetEmailEditText.text.toString()
        println(currentEmail)
        val result = auth.sendPasswordResetEmail(currentEmail)
        result.addOnCompleteListener{
            task ->
            if(task.isSuccessful){
                println("E postanızı Kontrol ediniz...")
            }
            else{
                println("E posta gönderilemedi...")
            }

        }

    }
}
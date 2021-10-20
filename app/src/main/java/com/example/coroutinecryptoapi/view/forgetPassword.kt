package com.example.coroutinecryptoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coroutinecryptoapi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_forget_password.view.*
import java.lang.Exception

class forgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        sendForgetPasswordButton.setOnClickListener {
            forgetPassword()
        }
    }

    fun forgetPassword() {
        try {
            val auth = FirebaseAuth.getInstance()
            val currentEmail = forgetEmailEditText.text.toString()
            println(currentEmail)
            if ("@" in currentEmail) {
                val result = auth.sendPasswordResetEmail(currentEmail)
                result.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Eposta Adresinizi kontrol ediniz...", Toast.LENGTH_LONG)
                        println("${currentEmail}")
                    } else {
                        println("E posta gönderilemedi...")
                        Toast.makeText(this,"Eposta Gönderilemedi",Toast.LENGTH_LONG).show()
                    }

                }

            }
            else{
                println("Lütfen Geçerli bir eposta adresi giriniz...")
                Toast.makeText(this,"Lütfen Geçerli Bir Eposta Adresi Giriniz...",Toast.LENGTH_LONG).show()
            }

        }catch (e:Exception){
            println(e.localizedMessage)
        }
    }
}


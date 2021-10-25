package com.example.coroutinecryptoapi.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.settinsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class accountSettings : AppCompatActivity(),settinsAdapter.settingslistener{
    val settings:Array<String> = arrayOf("Hesap Bilgileri","Sifre Degiştir","Önceki Hesap İşlemleri","Profil Ayarları")
    val accaountInfo:Array<String> = arrayOf("Hesap Bakiye","Toplam Kazanc","İslem Tarihleri","KaydettiğimCoinler")
    var settingsPage:String? = null

    private var RecyleViewAdapterSettings:settinsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        settingsRecyclerView.layoutManager = layoutManager

        GlobalScope.launch(Dispatchers.Default) {
            settings(settings)
        }
        AccountİmageView.setOnClickListener {
            val intent = Intent(this,settingsProfile::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if(settingsPage == "Hesap Bilgileri"){
            settings(accaountInfo)
            println("${settingsPage}")
        }
        if(settingsPage=="Sifre Degiştir"){
            val user = FirebaseAuth.getInstance()
            val email = Firebase.auth.currentUser
            email!!.email?.let { user.sendPasswordResetEmail(it).addOnCompleteListener{
                task->
                if(task.isSuccessful){
                    println("Şifre Sıfırlama BAŞARILI")
                    Toast.makeText(this,"Şifre Değiştirme Linki Gönderildi",Toast.LENGTH_LONG).show()
                    Toast.makeText(this,"${email.email} Eposta Adresinizi Kontrol Edin",Toast.LENGTH_LONG).show()
                }
                else{
                    println("Şifre Sıfırlama BAŞARISIZ")
                    Toast.makeText(this,"Şifre Değiştirme Linki Gönderilemedi",Toast.LENGTH_LONG).show()
                }
            }
            }
        }

    }

    fun settings(settingsInfo: Array<String>){

        RecyleViewAdapterSettings = settinsAdapter(settingsInfo,this@accountSettings)
        settingsRecyclerView.adapter = RecyleViewAdapterSettings

    }

    override fun OnItemClick(settings: Array<String>, position:Int) {
        println("OnClick ${settings.get(position)}")
        settingsPage = settings.get(position)
        onResume()

        }

    override fun permissionRequest() {
        TODO("Not yet implemented")
    }

    fun goHome(view:View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    }

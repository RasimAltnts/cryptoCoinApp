package com.example.coroutinecryptoapi.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.settinsAdapter
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.android.synthetic.main.activity_main.*
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


    }

    override fun onResume() {
        super.onResume()
        if(settingsPage == "Hesap Bilgileri"){
            settings(accaountInfo)
            println("${settingsPage}")

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

    }

package com.example.coroutinecryptoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.settinsAdapter
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.profileRecyclerView
import kotlinx.android.synthetic.main.activity_settings_profile.*

class settingsProfile : AppCompatActivity(), settinsAdapter.settingslistener {
    //RecyleView Adapter List hazırlandı
    val settingsProfilText:Array<String> = arrayOf("Profil Resmini Güncelle")

    //RecyleView tanımlandı.
    private var settingsProfileAdapter:settinsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_profile)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        profileRecyclerView.layoutManager = layoutManager
        settings(settingsProfilText)
    }

    fun settings(settingsInfo: Array<String>){

        settingsProfileAdapter = settinsAdapter(settingsInfo,this@settingsProfile)
        profileRecyclerView.adapter = settingsProfileAdapter

    }

    override fun OnItemClick(settings: Array<String>, position: Int) {
        Toast.makeText(this,"${settings.get(position)}",Toast.LENGTH_SHORT).show()

    }
}
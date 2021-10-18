package com.example.coroutinecryptoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.settinsAdapter
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class accountSettings : AppCompatActivity(),settinsAdapter.settingslistener{

    private var RecyleViewAdapterSettings:settinsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        settingsRecyclerView.layoutManager = layoutManager

        GlobalScope.launch(Dispatchers.Default) {
            settings()

        }


    }
    suspend fun settings(){
        RecyleViewAdapterSettings = settinsAdapter(this@accountSettings)
        settingsRecyclerView.adapter = RecyleViewAdapterSettings

    }

    override fun OnItemClick(settings: Array<String>,position:Int) {
        println("OnClick ${settings.get(position)}")
    }

}
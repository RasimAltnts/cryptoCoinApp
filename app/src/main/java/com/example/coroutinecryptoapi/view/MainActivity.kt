package com.example.coroutinecryptoapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.Model.CryptoModel
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.adapter
import com.example.coroutinecryptoapi.service.serviceAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), adapter.Listener {
    //API service BASE URL tanımlandı....
    private val BASE_URL = "https://api.nomics.com/v1/"
    //Model Tanımlama işlemi gerçekleştirildi...
    private var cryptoModels:ArrayList<CryptoModel>? = null
    //Mainpage recyclerViewAdapter nesnesi türetildi...
    private var recyclerViewSettingsAdapter : adapter? = null

    /*
    İzin işlemleri gerçekleştildi.
     */

    private val permissionRequired = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    private val permission_callback_constat = 100
    private val request_permission_settings = 101

    //ActivityFinish Request Code
    private val requestCode = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Working ${Thread.currentThread().name}")
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        profileRecyclerView.layoutManager = layoutManager

        GlobalScope.launch(Dispatchers.Default) {
            //println("Working ${Thread.currentThread().name}")
            loadData()
        }

        settingsİmageView.setOnClickListener {
            settingsOnClick()

        }
        AccountİmageView.setOnClickListener {
            val intent = Intent(this,settingsProfile::class.java)
            startActivity(intent)
        }

    }






    //Retrofit ile dataları JSON formatında çekme işlemi gerçekleştirildi...
    suspend fun loadData(){
        println("Working ${Thread.currentThread().name}")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val services =retrofit.create(serviceAPI::class.java)
        val call = services.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body().let {
                        cryptoModels = ArrayList(it)
                        for (cryptoModel:CryptoModel in cryptoModels!!){
                        }
                        recyclerViewSettingsAdapter = adapter(cryptoModels!!,this@MainActivity)
                        profileRecyclerView.adapter = recyclerViewSettingsAdapter

                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                println("onFail")
            }

        })
    }
    // Retrofit ile çekme işlemi yapıldı


    //RecyleViewAdapter Interface OnItenClick Listener Function Tanımnlandıı
    override fun OnItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }


    //Settings İmageView Settings sayfasına intent olarak uygulamayı settings sayfasına yönlendirme işlemi gerçekleştirdi..
    fun settingsOnClick(){
        Toast.makeText(this,"Thread:${Thread.currentThread().name}",Toast.LENGTH_LONG).show()
        val intent=Intent(this,accountSettings::class.java)
        startActivity(intent)
    }

}
package com.example.coroutinecryptoapi.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.R
import com.example.coroutinecryptoapi.adapter.settinsAdapter
import kotlinx.android.synthetic.main.activity_account_settings.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.profileRecyclerView
import kotlinx.android.synthetic.main.activity_settings_profile.*
import com.google.firebase.auth.FirebaseAuth

class settingsProfile : AppCompatActivity(), settinsAdapter.settingslistener {
    //RecyleView Adapter List hazırlandı
    val settingsProfilText:Array<String> = arrayOf("Profil Resmini Güncelle","Log Out")
    var currentAction:String ?= null
    var selectedImage: Uri? =  null
    private lateinit var auth:FirebaseAuth

    //RecyleView tanımlandı.
    private var settingsProfileAdapter:settinsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_profile)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        profileRecyclerView.layoutManager = layoutManager
        settings(settingsProfilText)
        val auth = FirebaseAuth.getInstance()
    }

    fun settings(settingsInfo: Array<String>){

        settingsProfileAdapter = settinsAdapter(settingsInfo,this@settingsProfile)
        profileRecyclerView.adapter = settingsProfileAdapter

    }

    override fun OnItemClick(settings: Array<String>, position: Int) {
        Toast.makeText(this,"${settings.get(position)}",Toast.LENGTH_SHORT).show()
        currentAction = settings.get(position)
        if(currentAction == "Profil Resmini Güncelle"){
            permissionRequest()
        }
        if(currentAction == "Log Out"){
            println("Log Out")
            auth.signOut()
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun permissionRequest(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2 && resultCode == Activity.RESULT_OK && data != null){
            selectedImage = data.data
            try{
                if(selectedImage != null){
                    if(Build.VERSION.SDK_INT >= 28){
                        val source = ImageDecoder.createSource(this.contentResolver,selectedImage!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        val imageview:ImageView = findViewById(R.id.AccountİmageView)
                        imageview.setImageBitmap(bitmap)


                    }
                }
            }catch (e:Exception){
                println(e.localizedMessage)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
package com.example.coroutinecryptoapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinecryptoapi.Model.CryptoModel
import com.example.coroutinecryptoapi.R
import kotlinx.android.synthetic.main.settings.view.*

class settinsAdapter(val settings:Array<String> ,private val listener: settinsAdapter.settingslistener):RecyclerView.Adapter<settinsAdapter.RowHolder>() {

    interface settingslistener{
            fun OnItemClick(settings:Array<String>,position: Int)
    }

    class RowHolder(view:View): RecyclerView.ViewHolder(view){
        fun bind(settingsList:Array<String>,position: Int,listener: settingslistener) {
            itemView.setOnClickListener {
                listener.OnItemClick(settingsList,position)
            }
            itemView.settingsTextView.text = settingsList[position]

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(settings,position,listener)
    }

    override fun getItemCount(): Int {
        return settings.size
    }

}
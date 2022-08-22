package com.example.homeservicefinal


import android.content.Context
import android.content.Intent

import android.net.Uri
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.homeservicefinal.MyAdapter.*
import java.util.*
import kotlin.collections.ArrayList


class MyAdapter(private val userlist : ArrayList<User>) : RecyclerView.Adapter<MyViewHolder>(),TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.plumberdatas, parent, false)

        tts =TextToSpeech(itemView.context,this)
        return MyViewHolder(itemView)
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userlist[position]
        holder.id.text = user.id
        holder.name.text = user.name
        holder.phone.text = user.phone
        holder.emai.text =user.emails
        holder.addre.text=user.addresss


        holder.itemView.findViewById<View>(R.id.bookn).setOnClickListener(View.OnClickListener() {
            val dialIntent = Intent(Intent.ACTION_DIAL)

            dialIntent.data = Uri.parse("tel:" + holder.phone.text.toString())
            var context=holder.id.context
            context.startActivity(dialIntent)

        });
        holder.itemView.findViewById<View>(R.id.textplumber).setOnClickListener(View.OnClickListener {


            tts!!.speak(holder.name.text, TextToSpeech.QUEUE_FLUSH, null,"")

        })





    }


    override fun getItemCount(): Int {
        return userlist.size

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val name: TextView = itemView.findViewById(R.id.plumbername)
        val id: TextView = itemView.findViewById(R.id.plumberid)
        val phone: TextView = itemView.findViewById(R.id.plumberphone)
        val emai:TextView = itemView.findViewById(R.id.plumberem)
        val addre:TextView = itemView.findViewById(R.id.plumberaddr)




    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }

    }



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
import com.example.homeservicefinal.MyAdapter2.*
import java.util.*
import kotlin.collections.ArrayList


class MyAdapter2(private val userlist2 : ArrayList<User2>) : RecyclerView.Adapter<MyViewHolder>(),TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.electriciandatas, parent, false)

        tts =TextToSpeech(itemView.context,this)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User2 = userlist2[position]
        holder.id.text = user.id
        holder.name.text = user.name
        holder.phone.text = user.phone
        holder.emai.text =user.email
        holder.addres.text=user.address



        holder.itemView.findViewById<View>(R.id.bookn2).setOnClickListener(View.OnClickListener() {
            val dialIntent = Intent(Intent.ACTION_DIAL)

            dialIntent.data = Uri.parse("tel:" + holder.phone.text.toString())
            var context=holder.id.context
            context.startActivity(dialIntent)

        });
        holder.itemView.findViewById<View>(R.id.textelectrician).setOnClickListener(View.OnClickListener {


            tts!!.speak(holder.name.text, TextToSpeech.QUEUE_FLUSH, null,"")

        })





    }


    override fun getItemCount(): Int {
        return userlist2.size

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val name: TextView = itemView.findViewById(R.id.electriname)
        val id: TextView = itemView.findViewById(R.id.electriid)
        val phone: TextView = itemView.findViewById(R.id.electriphone)
        val emai:TextView = itemView.findViewById(R.id.electriem)
        val addres:TextView = itemView.findViewById(R.id.electriaddr)




    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }


}



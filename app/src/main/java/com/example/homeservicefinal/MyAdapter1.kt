package com.example.homeservicefinal


import android.content.Intent

import android.net.Uri
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.homeservicefinal.MyAdapter1.*
import java.util.*
import kotlin.collections.ArrayList


class MyAdapter1(private val userlist1 : ArrayList<User1>) : RecyclerView.Adapter<MyViewHolder>() ,TextToSpeech.OnInitListener{
    private var tts: TextToSpeech? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.techniciandatas, parent, false)

        tts =TextToSpeech(itemView.context,this)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User1 = userlist1[position]
        holder.id.text = user.id
        holder.name.text = user.name
        holder.phone.text = user.phone
        holder.emai.text =user.email
        holder.addres.text=user.address



        holder.itemView.findViewById<View>(R.id.bookn1).setOnClickListener(View.OnClickListener() {
            val dialIntent = Intent(Intent.ACTION_DIAL)

            dialIntent.data = Uri.parse("tel:" + holder.phone.text.toString())
            var context=holder.id.context
            context.startActivity(dialIntent)

        });
        holder.itemView.findViewById<View>(R.id.texttechnician).setOnClickListener(View.OnClickListener {


            tts!!.speak(holder.name.text, TextToSpeech.QUEUE_FLUSH, null,"")

        })





    }


    override fun getItemCount(): Int {
        return userlist1.size

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val name: TextView = itemView.findViewById(R.id.techniciname)
        val id: TextView = itemView.findViewById(R.id.techniciid)
        val phone: TextView = itemView.findViewById(R.id.techniciphone)
        val emai:TextView = itemView.findViewById(R.id.techniciem)
        val addres:TextView = itemView.findViewById(R.id.techniciaddr)




    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
        }
    }
}



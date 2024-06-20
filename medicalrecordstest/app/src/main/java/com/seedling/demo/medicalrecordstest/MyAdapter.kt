package com.seedling.demo.medicalrecordstest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class MyAdapter(private val users:JSONArray,private val callb: (pos:Int) -> Unit):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val listItem=layoutInflater.inflate(R.layout.item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user=users.getJSONObject(position)
        holder.bind(user.getInt("id"), user.getString("name").toString(), position, callb)
    }

    override fun getItemCount(): Int {
        return users.length()
    }

}
    class MyViewHolder(val view:View):RecyclerView.ViewHolder(view){
        fun bind(ident: Int, nam: String,pos:Int,callb:(pos:Int)->Unit){
            view.findViewById<TextView>(R.id.iden).setText(ident.toString())
            view.findViewById<TextView>(R.id.name).setText(nam)

            view.setOnClickListener {
                callb(pos)

            }
    }
}
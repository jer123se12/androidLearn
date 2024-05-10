package com.seedling.demo.recyclethis

import android.health.connect.datatypes.units.Length
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyrvAdapter(private val texts:List<Fruit>,private val cl:(Fruit) -> Unit):RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val listItem=layoutInflater.inflate(R.layout.list_item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return texts.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(texts[position],cl)

    }

}
class MyViewHolder(val view: View):RecyclerView.ViewHolder(view){
    fun bind(fruit:Fruit, cl:(Fruit) -> Unit) {
        val tv = view.findViewById<TextView>(R.id.tvName)
        val sup = view.findViewById<TextView>(R.id.sup)
        tv.text=fruit.name
        sup.text=fruit.supplier
        view.setOnClickListener {
//            Toast.makeText(view.context,"selected fruit: ${fruit.name}",Toast.LENGTH_LONG).show()
            cl(fruit)
        }
    }

}
package com.seedling.demo.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.util.Log
data class info(val va:String)
object InfoStorage{
    private var v: String=""
    fun changeString(s:String){
        v=s
    }
    fun getString(): String{
        return v
    }
}
class FirstFrag : Fragment(R.layout.fragment_first) {
    private val s=InfoStorage
    private lateinit var activity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity=context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.submit).setOnClickListener {
            val otherFrag=SecondFrag()
            val value=view.findViewById<EditText>(R.id.stuff).text
            s.changeString(value.toString())
//            val bundle = Bundle()
//            bundle.putString("v", value.toString())
//            otherFrag.arguments=bundle



        }
    }
}
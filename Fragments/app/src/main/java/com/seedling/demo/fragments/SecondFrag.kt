package com.seedling.demo.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment


class SecondFrag : Fragment(R.layout.fragment_second) {
    private val s=InfoStorage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val value= arguments?.getString("v")
        val value=s.getString()
        view.findViewById<TextView>(R.id.tvSecondFrag).setText(value)
    }




}
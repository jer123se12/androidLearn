package com.seedling.demo.medicalrecordstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiContext
import androidx.fragment.app.Fragment
import org.json.JSONObject
import android.util.Log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [MedicalRecs.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalRecs : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonstring= requireContext().assets.open("records.json").bufferedReader().use{it.readText()}
        val json=JSONObject(jsonstring)
        Log.i("mine", json.toString())






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_recs, container, false)
    }

}
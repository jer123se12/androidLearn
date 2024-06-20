package com.seedling.demo.medicalrecordstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONArray


class details : Fragment() {
    // TODO: Rename and change types of parameters
    var position:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position=arguments?.getInt("position")!!
        val jsonstring= requireContext().assets.open("records.json").bufferedReader().use{it.readText()}
        val info=JSONArray(jsonstring).getJSONObject(position)
        val resultstv=view.findViewById<TextView>(R.id.resultstv)
        val conditionstv=view.findViewById<TextView>(R.id.conditionstv)
        var resultt=""
        val resul=info.getJSONObject("lab_result")
        for (key:String in resul.keys()){
            resultt+=key+": "+resul.getString(key)+"\n"
        }
        var conditionst=""
        val conditions=info.getJSONObject("condition")
        for (key:String in conditions.keys()){
            conditionst+=key+": "+conditions.getString(key)+"\n"
        }

        view.findViewById<TextView>(R.id.detailname).setText(info.getString("name"))
        resultstv.setText(resultt)
        conditionstv.setText(conditionst)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        position= arguments?.getInt("position")!!

        return inflater.inflate(R.layout.fragment_details, container, false)
    }


}
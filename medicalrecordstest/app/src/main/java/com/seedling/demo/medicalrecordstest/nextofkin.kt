package com.seedling.demo.medicalrecordstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.io.File

class nextofkin : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file: File = File(requireContext().filesDir, "profile.json")
        val jsonstring=file.readText()
        val useri= JSONObject(jsonstring)
        val c1=view?.findViewById<EditText>(R.id.c1)
        val c2=view?.findViewById<EditText>(R.id.c2)
        c1?.setText(useri.getJSONObject("nextofkin").getString("contact 1"))
        c2?.setText(useri.getJSONObject("nextofkin").getString("contact 2"))
        view?.findViewById<Button>(R.id.ubtn)?.setOnClickListener{
            useri.getJSONObject("nextofkin").put("contact 1",c1?.text)
            useri.getJSONObject("nextofkin").put("contact 2",c2?.text)
            val outputStream =
                requireContext().openFileOutput("profile.json", AppCompatActivity.MODE_PRIVATE)
            outputStream.write(useri.toString().toByteArray())
            outputStream.close()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nextofkin, container, false)
    }
}
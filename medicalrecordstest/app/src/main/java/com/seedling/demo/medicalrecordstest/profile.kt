package com.seedling.demo.medicalrecordstest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.io.File
import kotlin.contracts.contract

class profile : Fragment() {
    private lateinit var galleryimg: ImageView
    private var mygal=registerForActivityResult(ActivityResultContracts.GetContent(),
        ActivityResultCallback {
            galleryimg.setImageURI(it)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file: File = File(requireContext().filesDir, "profile.json")
        if (!file.exists()) {
            Log.i("mine","made new file")
            val outputStream =
                requireContext().openFileOutput("profile.json", AppCompatActivity.MODE_PRIVATE)
            val text =
                requireContext().assets.open("profile.json").bufferedReader().use { it.readText() }
            outputStream.write(text.toByteArray())
            outputStream.close()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file: File = File(requireContext().filesDir, "profile.json")
        val jsonstring=file.readText()
        val useri= JSONObject(jsonstring)
        val userinfo= view?.findViewById<EditText>(R.id.userinfo)
        val nextofkin= view?.findViewById<TextView>(R.id.userinfo2)
        Log.i("mine", useri.toString())
        userinfo?.setText(useri.getString("userinfo"))
        Log.i("mine", file.canonicalPath)
        userinfo?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val texts = userinfo?.text.toString()
                useri.put("userinfo", texts)
                Log.i("mine", useri.toString())
                val outputStream =
                    requireContext().openFileOutput("profile.json", AppCompatActivity.MODE_PRIVATE)
                outputStream.write(useri.toString().toByteArray())
                outputStream.close()
            }
        }
        val final="Contact 1: "+useri.getJSONObject("nextofkin").getString("contact 1")+
                "\nContact 2: "+useri.getJSONObject("nextofkin").getString("contact 2")+"\nclick to edit"
        nextofkin?.setText(final)
        nextofkin?.setOnClickListener{
            val detai = nextofkin()
            val fragmentManager = parentFragmentManager
            val trans = fragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainerView, detai)
            trans.addToBackStack(null)
            trans.commit()
        }
        val img=view?.findViewById<ImageView>(R.id.imageView)
        img?.isClickable=true
        img?.setOnClickListener{
            mygal.launch("image/*")

        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}
package com.seedling.demo.medicalrecordstest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.seedling.demo.medicalrecordstest.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val file: File = File(filesDir, "records.json")
        if (!file.exists()) {
            val outputStream=openFileOutput("records.json", MODE_PRIVATE)
            val text=assets.open("records.json").bufferedReader().use{it.readText()}
            outputStream.write(text.toByteArray())
            outputStream.close()
        }


        binding.topAppBar.setNavigationOnClickListener {
            binding.draw.open()
        }
        gotofrag(MedicalRecs())
        binding.nav.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.profile->gotofrag(profile())
                R.id.recs->gotofrag(MedicalRecs())
                R.id.jour->gotofrag(journal())
                else->{

                }
            }
            binding.draw.close()
            true
        }


    }
    private fun gotofrag(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val trans=fragmentManager.beginTransaction()
        trans.replace(R.id.fragmentContainerView,fragment)
        trans.addToBackStack(null)
        trans.commit()
    }




}


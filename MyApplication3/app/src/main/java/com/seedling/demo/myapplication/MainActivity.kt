package com.seedling.demo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.seedling.demo.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Profile())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.profile->replaceFragment(Profile())
                R.id.records->replaceFragment(records())
                R.id.patient->replaceFragment(patient())
                else ->{

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fram,fragment)
        fragmentTransaction.commit()
    }
}
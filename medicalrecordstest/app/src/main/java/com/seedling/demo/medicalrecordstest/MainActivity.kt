package com.seedling.demo.medicalrecordstest
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.seedling.demo.medicalrecordstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            binding.draw.open()
        }
        gotofrag(profile())
        binding.nav.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.profile->gotofrag(profile())
                R.id.recs->gotofrag(MedicalRecs())
                R.id.jour->gotofrag(journal())
                else->{

                }
            }
            true
        }


    }
    private fun gotofrag(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val trans=fragmentManager.beginTransaction()
        trans.replace(R.id.fragmentContainerView,fragment)
        trans.commit()
    }
}

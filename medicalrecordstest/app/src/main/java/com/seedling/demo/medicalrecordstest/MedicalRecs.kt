package com.seedling.demo.medicalrecordstest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.File

/**
 * A simple [Fragment] subclass.
 * Use the [MedicalRecs.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalRecs : Fragment() {

    private lateinit var users:JSONArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file: File = File(requireContext().filesDir, "records.json")
        val jsonstring=file.readText()
        users=JSONArray(jsonstring)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myrv=view.findViewById<RecyclerView>(R.id.myrv)
        val itemAdapter=MyAdapter(users){
            var bundle=Bundle()
            bundle.putInt("position", it)

            val detai=details()
            detai.arguments=bundle
            val fragmentManager=parentFragmentManager
            val trans=fragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainerView,detai)
            trans.addToBackStack(null)
            trans.commit()
        }
        myrv.layoutManager=LinearLayoutManager(context)
        myrv.adapter=itemAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_recs, container, false)
    }

}
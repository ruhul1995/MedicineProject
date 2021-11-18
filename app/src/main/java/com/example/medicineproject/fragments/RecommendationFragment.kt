package com.example.medicineproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicineproject.MainActivity.Companion.viewModel
import com.example.medicineproject.R
import com.example.medicineproject.adapters.MedicineListAdapter
import com.example.medicineproject.model.MedicineModel
import com.example.medicineproject.viewModel.MedicineListViewModel

class RecommendationFragment : Fragment() {

    private  var  medicineModelList: List<MedicineModel> = emptyList()
    private lateinit var adapter: MedicineListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewlist = view.findViewById(R.id.recyclerViewRcmd) as RecyclerView
        recyclerViewlist.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        val layoutManager = LinearLayoutManager(activity)
        recyclerViewlist.setLayoutManager(layoutManager)
        recyclerViewlist.setHasFixedSize(true)
        adapter = MedicineListAdapter(requireActivity().applicationContext, medicineModelList)
        adapter.setMedicineList(medicineModelList)
        recyclerViewlist.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())[MedicineListViewModel::class.java]
        viewModel.getMedicineListObservablesForSR().observe(viewLifecycleOwner,
            Observer<List<MedicineModel>?>
            { medicineModels: List<MedicineModel>? ->
                if (medicineModels != null) {
                    Log.d("value : ", medicineModels.toString())
                    medicineModelList = medicineModels
                    adapter.setMedicineList(medicineModelList)
                } else {
                    Log.d("error : ", viewModel.toString())
                }
            })
    }
}
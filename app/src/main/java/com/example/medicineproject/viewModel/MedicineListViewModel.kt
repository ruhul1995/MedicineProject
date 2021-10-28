package com.example.medicineproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicineproject.adapters.MedicineListAdapter
import com.example.medicineproject.model.ApiInterface
import com.example.medicineproject.model.MedicineModel
import com.example.medicineproject.model.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicineListViewModel() : ViewModel() {

    private var medicineList: MutableLiveData<List<MedicineModel>> =  MutableLiveData()
    private var medicineListSR : MutableLiveData<List<MedicineModel>> = MutableLiveData()
    var listSmartRecomd : MutableList<MedicineModel> = arrayListOf()
    var listNotSmartRecomd : MutableList<MedicineModel> = arrayListOf()

    fun MedicineListViewModel() {
        medicineList = MutableLiveData<List<MedicineModel>>()
    }

    fun getMedicineListObservables(): MutableLiveData<List<MedicineModel>> {
        return medicineList
    }
    fun getMedicineListObservablesForSR():MutableLiveData<List<MedicineModel>>
    {
        return medicineListSR
    }
    fun makeApiCall() {
        val apiInterface: ApiInterface? =
            RetrofitInstance.retrofitInstance?.create(ApiInterface::class.java)
        val call: Call<List<MedicineModel>>? = apiInterface?.getMedicineList("4990224")
        call?.enqueue(object : Callback<List<MedicineModel>?> {

            override fun onResponse(call: Call<List<MedicineModel>?>, response: Response<List<MedicineModel>?>) {
                var resource: List<MedicineModel>? = response.body()
                var mylist = resource

                //var listSmartRecomd : MutableList<MedicineModel> = arrayListOf()
                //var listNotSmartRecomd : MutableList<MedicineModel> = arrayListOf()

                for( i in resource!!.indices)
                {
                    if(resource.get(i).smartRecommendation == true) {
                        listSmartRecomd.addAll(listOf(resource[i]))
                        medicineListSR.postValue(listSmartRecomd)
                    }
                    else {
                        listNotSmartRecomd.addAll(listOf(resource[i]))
                        medicineList.postValue(listNotSmartRecomd)
                    }
                }

                //medicineList.postValue(listNotSmartRecomd)



            }

            override fun onFailure(call: Call<List<MedicineModel>?>, t: Throwable) {
                Log.d("error : ", "Api call was not successfull")
                medicineList?.postValue(null)
            }
        })
    }

}
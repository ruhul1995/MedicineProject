package com.example.medicineproject.Repository

import android.util.Log
import com.example.medicineproject.model.ApiInterface
import com.example.medicineproject.model.MedicineModel
import com.example.medicineproject.model.RetrofitInstance
import com.example.medicineproject.viewModel.MedicineListViewModel
import com.example.medicineproject.viewModel.MedicineListViewModel.Companion.medicineList
import com.example.medicineproject.viewModel.MedicineListViewModel.Companion.medicineListSR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MyRepository {

    var listSmartRecomd : MutableList<MedicineModel> = arrayListOf()
    var listNotSmartRecomd : MutableList<MedicineModel> = arrayListOf()

    //suspend keyword. This keyword is Kotlin's way to enforce a function to be called from within a coroutine.
    suspend fun makeApiCall() {

        //making our calling function as main safe and enabling the UI to update as needed.
        return withContext(Dispatchers.IO){

            //Blocking network request code

            val apiInterface: ApiInterface? = RetrofitInstance.retrofitInstance?.create(ApiInterface::class.java)
            val call: Call<List<MedicineModel>>? = apiInterface?.getMedicineList("4990224")
            call?.enqueue(object : Callback<List<MedicineModel>?> {

                override fun onResponse(call: Call<List<MedicineModel>?>, response: Response<List<MedicineModel>?>) {
                    var resource: List<MedicineModel>? = response.body()
                    for( i in resource!!.indices)
                    {
                        if(resource[i].smartRecommendation) {
                            listSmartRecomd.addAll(listOf(resource[i]))
                            medicineListSR.postValue(listSmartRecomd)
                        }
                        else {
                            listNotSmartRecomd.addAll(listOf(resource[i]))
                            medicineList.postValue(listNotSmartRecomd)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MedicineModel>?>, t: Throwable) {
                    Log.d("error : ", "Api call was not successfull")
                    medicineList?.postValue(null)
                }
            })
        }
    }
}
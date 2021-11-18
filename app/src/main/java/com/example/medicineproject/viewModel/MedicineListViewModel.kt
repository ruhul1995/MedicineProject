package com.example.medicineproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineproject.Repository.MyRepository
import com.example.medicineproject.model.MedicineModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
class MedicineListViewModel() : ViewModel() {

    companion object {
         var medicineList: MutableLiveData<List<MedicineModel>> =  MutableLiveData()
         var medicineListSR : MutableLiveData<List<MedicineModel>> = MutableLiveData()
    }

    fun MedicineListViewModel() {
        medicineList = MutableLiveData<List<MedicineModel>>()
    }

    fun getMedicineListObservables(): MutableLiveData<List<MedicineModel>> {
        return medicineList
    }
    fun getMedicineListObservablesForSR(): MutableLiveData<List<MedicineModel>>
    {
        return medicineListSR
    }

    var myRepository: MyRepository = MyRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // Make the network call and suspend execution until it finishes
            val result = try {
                myRepository.makeApiCall()
            }
            catch (e : Exception){
                Result.Error(Exception("Network request failed"))
            }

            // Display result of the network request to the user
            when(result){
                is Result.Success<*> -> // Happy Path
                    Result.Success("Successfully Called API")
                else-> // Show error in UI
                    Result.Error(Exception("Not Data Available"))
            }
        }
    }
}
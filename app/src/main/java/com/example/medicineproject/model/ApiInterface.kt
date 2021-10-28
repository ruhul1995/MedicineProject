package com.example.medicineproject.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //@GET("/Retailers/4990224/fetchFrequentlyOrderedProduct?retailerId=4990224")
    @GET("/api//Retailers/4990224/fetchFrequentlyOrderedProduct?")
    fun getMedicineList(@Query("retailerId") title: String?): Call<List<MedicineModel>>
}
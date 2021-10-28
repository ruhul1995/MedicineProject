package com.example.medicineproject.model

import com.google.gson.annotations.SerializedName


data class MedicineModel (

    @SerializedName("productName") val productName : String,
    @SerializedName("displayText") val displayText : String,
    @SerializedName("schemeLabelForRetailer") val schemeLabelForRetailer : String,
    @SerializedName("distributorName") val distributorName : String,
    @SerializedName("manufacturerName") val manufacturerName : String,
    @SerializedName("mrp") val mrp : Double,
    @SerializedName("ptr") val ptr : Double,
    @SerializedName("stock") val stock : Int,
    @SerializedName("productUrl") val productUrl : String,
    @SerializedName("smartRecommendation") val smartRecommendation : Boolean
)

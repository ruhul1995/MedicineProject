package com.example.medicineproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicineproject.R
import com.example.medicineproject.model.MedicineModel

class MedicineListAdapter(private var context: Context, private var medicineList: List<MedicineModel>) :
    RecyclerView.Adapter<MedicineListAdapter.ViewHolder>() {

      //for modifying the data
    fun setMedicineList(medicineList: List<MedicineModel>) {
        this.medicineList = medicineList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var obj: MedicineModel = medicineList!![position]

        holder.mMedicineName.text = obj.productName
        holder.mQuantity.text = obj.displayText
        holder.mSchemes.text = obj.schemeLabelForRetailer
        holder.mPlaceName.text = obj.distributorName
        holder.mCompanyName.text = obj.manufacturerName
        holder.mDiscountPrice.text = "₹ "+obj.ptr.toString()
        holder.mMaxRetailPrice.text = "₹ "+obj.mrp.toString()
        holder.mOrderQuantity.setText("Qty "+obj.stock.toString())

        val medicineImagePath =  obj.productUrl

        Glide.with(context).load(medicineImagePath).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return if (medicineList != null) medicineList.size else 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mMedicineName: TextView
        var mQuantity: TextView
        var mSchemes: TextView
        var mPlaceName: TextView
        var mCompanyName: TextView
        var mDiscountPrice: TextView
        var mMaxRetailPrice: TextView
        var mOrderQuantity: TextView

        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
            mMedicineName = itemView.findViewById(R.id.medicineName)
            mQuantity = itemView.findViewById(R.id.quantity)
            mSchemes = itemView.findViewById(R.id.schemes)
            mPlaceName = itemView.findViewById(R.id.placeName)
            mCompanyName = itemView.findViewById(R.id.companyName)
            mDiscountPrice = itemView.findViewById(R.id.discountPrice)
            mMaxRetailPrice = itemView.findViewById(R.id.maxRetailPrice)
            mOrderQuantity = itemView.findViewById(R.id.orderedQuantityNumber)
        }
    }

}
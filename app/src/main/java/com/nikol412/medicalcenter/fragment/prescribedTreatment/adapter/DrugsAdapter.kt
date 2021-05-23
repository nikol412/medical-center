package com.nikol412.medicalcenter.fragment.prescribedTreatment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.medicalcenter.databinding.ItemPrescribedDrugRowBinding
import com.nikol412.medicalcenter.db.models.Drug

class DrugsAdapter : RecyclerView.Adapter<DrugViewHolder>() {
    private var drugsList = mutableListOf<Drug>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        return DrugViewHolder(
            ItemPrescribedDrugRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        holder.onBind(drugsList[position])
    }

    override fun getItemCount(): Int = drugsList.size

    fun setItems(newItems: List<Drug>) {
        drugsList = newItems.toMutableList()
        notifyDataSetChanged()
    }
}

class DrugViewHolder(private val binding: ItemPrescribedDrugRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(drug: Drug) {
        with(binding) {
            textViewPrescribedDrugName.text = drug.name
            textViewPrescribedDrugDays.text = "${drug.numberOfDays}"
            textViewPrescribedDrugDoses.text = "${drug.numberOfDoses}"
        }
    }
}
package com.nikol412.medicalcenter.fragment.medicalCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.medicalcenter.databinding.ItemMedicalCardRowBinding
import com.nikol412.medicalcenter.db.models.Appointment

class MedicalCardsAdapter(private val listener: onMedicalCardClickListener) : RecyclerView.Adapter<MedicalCardViewHolder>() {
    private var appointmentsList = mutableListOf<Appointment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalCardViewHolder {
        return MedicalCardViewHolder(
            ItemMedicalCardRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MedicalCardViewHolder, position: Int) {
        holder.onBind(appointmentsList[position], listener)
    }

    override fun getItemCount(): Int = appointmentsList.size

    fun setItems(newItems: List<Appointment>) {
        appointmentsList = newItems.toMutableList()
        notifyDataSetChanged()
    }
}

class MedicalCardViewHolder(private val binding: ItemMedicalCardRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(appointment: Appointment, listener: onMedicalCardClickListener) {
        binding.cardParent.setOnClickListener {
            listener.onClick(appointment.id)
        }
        binding.textViewAppointmentDateTitle.text = "Запись приема от ${appointment.date}"
        binding.textViewAppointmentDoctor.text = "Врач: ${appointment.doctor?.speciality}"
    }
}

interface onMedicalCardClickListener {
    fun onClick(id: Int)
}
package com.nikol412.medicalcenter.fragment.prescribedTreatment

import androidx.lifecycle.MutableLiveData
import com.nikol412.medicalcenter.db.models.Drug
import com.nikol412.medicalcenter.db.repository.AppointmentRepository
import com.nikol412.medicalcenter.fragment.BaseViewModel

class PrescribedTreatmentViewModel : BaseViewModel() {
    val drugs = MutableLiveData<List<Drug>>()

    private val appointmentRepository = AppointmentRepository()

    fun fetchDrugsForAppointment(appointmentId: Int) {
        compositeDisposable.add(
            appointmentRepository.getAppointmentById(appointmentId)
                .subscribe { appointment ->
                    appointment.diagnosis?.drugsId?.let { prescribedDrugs ->
                        drugs.value = prescribedDrugs
                    }
                }
        )
    }
}
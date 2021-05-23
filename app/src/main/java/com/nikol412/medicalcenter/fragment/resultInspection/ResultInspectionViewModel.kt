package com.nikol412.medicalcenter.fragment.resultInspection

import androidx.lifecycle.MutableLiveData
import com.nikol412.medicalcenter.db.repository.AppointmentRepository
import com.nikol412.medicalcenter.fragment.BaseViewModel

class ResultInspectionViewModel : BaseViewModel() {
    val resultInspectionText = MutableLiveData("")

    private val appointmentRepository = AppointmentRepository()

    fun fetchInspectionResult(id: Int) {
        compositeDisposable.add(
            appointmentRepository.getAppointmentById(id)
                .subscribe { appointment ->
                    appointment.diagnosis?.resultInspection?.let { result ->
                        resultInspectionText.value = result
                    }
                }
        )
    }
}
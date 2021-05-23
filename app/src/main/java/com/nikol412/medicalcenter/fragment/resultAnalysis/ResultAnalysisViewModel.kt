package com.nikol412.medicalcenter.fragment.resultAnalysis

import androidx.lifecycle.MutableLiveData
import com.nikol412.medicalcenter.db.repository.AppointmentRepository
import com.nikol412.medicalcenter.fragment.BaseViewModel

class ResultAnalysisViewModel : BaseViewModel() {
    val resultOfAnalysis = MutableLiveData("")

    private val appointmentRepository = AppointmentRepository()

    fun fetchAnalysisResult(id: Int) {
        compositeDisposable.add(
            appointmentRepository.getAppointmentById(id)
                .subscribe { appointment ->
                    appointment.diagnosis?.resultAnalysis?.let { result ->
                        resultOfAnalysis.value = result
                    }
                }
        )
    }
}
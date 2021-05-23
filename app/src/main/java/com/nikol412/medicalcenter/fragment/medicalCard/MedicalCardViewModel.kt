package com.nikol412.medicalcenter.fragment.medicalCard

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.db.models.Appointment
import com.nikol412.medicalcenter.db.repository.AppointmentRepository
import com.nikol412.medicalcenter.fragment.BaseViewModel
import com.nikol412.medicalcenter.fragment.resultInspection.APPOINTMENT_ID

class MedicalCardViewModel : BaseViewModel() {
    private val appointmentRepository = AppointmentRepository()
    val appointments = MutableLiveData<List<Appointment>>()

    init {
        fetchAppointments()
    }

    private fun fetchAppointments() {
        compositeDisposable.add(
            appointmentRepository.getAppointmentsFlowable()
                .subscribe { list ->
                    appointments.value = list
                }
        )
    }

    fun onWatchSessionClick(appointmentId: Int) {
        navController?.navigate(
            R.id.sessionRecordFragment,
            bundleOf(APPOINTMENT_ID to appointmentId)
        )
    }
}

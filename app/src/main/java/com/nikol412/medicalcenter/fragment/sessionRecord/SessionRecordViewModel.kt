package com.nikol412.medicalcenter.fragment.sessionRecord

import androidx.core.os.bundleOf
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.fragment.BaseViewModel
import com.nikol412.medicalcenter.fragment.resultInspection.APPOINTMENT_ID

class SessionRecordViewModel : BaseViewModel() {
    var appointmentId: Int? = null

    fun onResultAppointmentClick() {
        navController?.navigate(
            R.id.resultInspectionFragment,
            bundleOf(APPOINTMENT_ID to appointmentId)
        )
    }

    fun onResultOfAnalysesClick() {
        navController?.navigate(
            R.id.resultAnalysisFragment,
            bundleOf(APPOINTMENT_ID to appointmentId)
        )
    }

    fun onPrescribedTreatmentClick() {
        navController?.navigate(R.id.prescribedTreatmentFragment)
    }

    fun onCertificateClick() {
        navController?.navigate(R.id.sessionCertificateFragment)
    }
}

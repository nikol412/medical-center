package com.nikol412.medicalcenter.fragment.sessionRecord

import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.fragment.BaseViewModel

class SessionRecordViewModel : BaseViewModel() {
    fun onResultAppointmentClick() {

    }

    fun onResultOfAnalysesClick() {

    }

    fun onPrescribedTreatmentClick() {
        navController?.navigate(R.id.prescribedTreatmentFragment)
    }

    fun onCertificateClick() {
        navController?.navigate(R.id.sessionCertificateFragment)
    }
}

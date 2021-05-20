package com.nikol412.medicalcenter.fragment.appointment

import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.fragment.BaseViewModel

class AppointmentViewModel : BaseViewModel() {

    fun onMakeAppointmentClick() {
        navController?.navigate(R.id.medicalCardFragment)
    }
}

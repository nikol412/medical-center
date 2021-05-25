package com.nikol412.medicalcenter.fragment.main

import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.fragment.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun onMakeAppointment() {
        navController?.navigate(R.id.appointmentFragment)
    }

    fun onWatchCardClick() {
        navController?.navigate(R.id.medicalCardFragment)
    }

}
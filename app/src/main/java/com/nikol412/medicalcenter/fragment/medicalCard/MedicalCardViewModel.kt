package com.nikol412.medicalcenter.fragment.medicalCard

import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.fragment.BaseViewModel

class MedicalCardViewModel : BaseViewModel() {

    fun onWatchSessionClick() {
        navController?.navigate(R.id.sessionRecordFragment)
    }
}

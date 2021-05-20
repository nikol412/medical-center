package com.nikol412.medicalcenter.fragment

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

abstract class BaseViewModel: ViewModel() {
    var navController: NavController? = null

}
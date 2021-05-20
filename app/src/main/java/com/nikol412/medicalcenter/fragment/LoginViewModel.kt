package com.nikol412.medicalcenter.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nikol412.medicalcenter.R

class LoginViewModel : ViewModel() {

    var navController: NavController? = null

    private val login = "Nika"
    private val password = "Nika"
    val userLogin = MutableLiveData("")
    val userPassword = MutableLiveData("")



    fun onLoginClick() {
        if (userLogin.value == login && userPassword.value == password) {
            navController?.navigate(R.id.mainFragment)
        }
    }
}
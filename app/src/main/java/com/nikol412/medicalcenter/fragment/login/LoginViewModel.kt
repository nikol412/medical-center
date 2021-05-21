package com.nikol412.medicalcenter.fragment.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.db.models.User
import com.nikol412.medicalcenter.db.repository.UserRepository

class LoginViewModel : ViewModel() {

    var navController: NavController? = null

    var userRepository = UserRepository()

    private val login = "Nika"
    private val password = "Nika"
    val userLogin = MutableLiveData("")
    val userPassword = MutableLiveData("")

    init {
        createUser()

    }
    fun onLoginClick() {
        if (userLogin.value == login && userPassword.value == password) {
            navController?.navigate(R.id.mainFragment)
        }
    }

    private fun createUser() {
        userRepository.createUser(User(login, password))
    }
}
package com.nikol412.medicalcenter.fragment.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.db.models.User
import com.nikol412.medicalcenter.db.repository.UserRepository
import com.nikol412.medicalcenter.extensions.safeLet

class LoginViewModel : ViewModel() {

    var navController: NavController? = null

    var userRepository = UserRepository()

    val userLogin = MutableLiveData("")
    val userPassword = MutableLiveData("")


    fun onLoginClick() {
        safeLet(userLogin.value, userPassword.value) { (login, password) ->
            val user = User(login, password)
            if (userRepository.isUserExist()) {
                if (userRepository.checkUserIsValid(user)) {
                    navigateToMain()
                }
            } else {
                userRepository.createUser(user)
                navigateToMain()
            }
        }
    }

    private fun navigateToMain() {
        navController?.navigate(R.id.mainFragment)
    }
}
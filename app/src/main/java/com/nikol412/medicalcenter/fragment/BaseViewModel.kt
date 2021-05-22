package com.nikol412.medicalcenter.fragment

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {
    var navController: NavController? = null

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
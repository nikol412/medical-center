package com.nikol412.medicalcenter.fragment.appointment

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.db.models.Appointment
import com.nikol412.medicalcenter.db.models.Doctor
import com.nikol412.medicalcenter.db.repository.AppointmentRepository
import com.nikol412.medicalcenter.db.repository.DoctorsRepository
import com.nikol412.medicalcenter.fragment.BaseViewModel

class AppointmentViewModel : BaseViewModel() {
    private val doctorsRepository = DoctorsRepository()
    private val appointmentsRepository = AppointmentRepository()

    val departments = MutableLiveData<List<String>>()

    val doctors = MutableLiveData<List<Doctor>>()

    var enteredSpeciality: String? = null
    var enteredDoctor: Doctor? = null

    var enteredDate = MutableLiveData<String>()

    var enteredTime = MutableLiveData<String>()

    val spinnerItemSelectedListenerDepartment = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val department = parent?.getItemAtPosition(position) as? String
            enteredSpeciality = department

            department?.let {
                fetchDoctors(it)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    val spinnerItemSelectedDoctors = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            enteredDoctor =
                doctors.value?.find { it.name == parent?.getItemAtPosition(position).toString() }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    init {
        fetchDepartments()
        fetchDoctors()
    }

    fun onMakeAppointmentClick() {
        if (enteredSpeciality != null && enteredDoctor != null
            && enteredDate.value != null && enteredTime.value != null
        ) {
            appointmentsRepository.createAppointment(
                Appointment(
                    0,
                    enteredDoctor!!,
                    enteredDate.value!!,
                    enteredTime.value!!
                )
            )
            compositeDisposable.add(
            appointmentsRepository.getAppointmentsFlowable()
                .subscribe {
                    Log.d("Realm", "doctor: ${it.firstOrNull()?.doctor} and date: ${it.firstOrNull()?.date}")
                }
            )
            navController?.navigate(R.id.mainFragment)
        }
    }

    private fun fetchDoctors(department: String? = null) {
        compositeDisposable.add(
            doctorsRepository.getDoctors()
                .subscribe { localDoctors ->
                    doctors.value = if (department != null)
                        localDoctors.filter { it.speciality == department }
                    else
                        localDoctors
                }
        )
    }

    private fun fetchDepartments() {
        compositeDisposable.add(
            doctorsRepository.getDoctors()
                .subscribe { localDoctors ->
                    departments.value = localDoctors.mapNotNull { it.speciality }.distinct()
                }
        )
    }
}


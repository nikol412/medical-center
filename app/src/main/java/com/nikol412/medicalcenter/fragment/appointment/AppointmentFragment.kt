package com.nikol412.medicalcenter.fragment.appointment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class AppointmentFragment : Fragment() {

    private val viewModel: AppointmentViewModel by viewModels()
    private lateinit var binding: FragmentAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)

        viewModel.navController = findNavController()
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.spinnerDepartments.onItemSelectedListener =
            viewModel.spinnerItemSelectedListenerDepartment
        binding.spinnerDoctors.onItemSelectedListener =
            viewModel.spinnerItemSelectedDoctors

        subscribeToVM()

        binding.textViewDatePicker.setOnClickListener {
            dateAlert()
        }
        return binding.root
    }

    private fun subscribeToVM() {
        viewModel.doctors.observe(viewLifecycleOwner, Observer { doctors ->
            val mapedList = doctors.mapNotNull { it.name }
            val doctorsAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mapedList
            )
            binding.spinnerDoctors.adapter = doctorsAdapter
        })

        viewModel.departments.observe(viewLifecycleOwner, Observer { list ->
            val spinnerDepartmentsAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                list
            )

            binding.spinnerDepartments.adapter = spinnerDepartmentsAdapter
        })

    }

    fun dateAlert() {
        val calendar = Calendar.getInstance();
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                changeDateFormatAndSave(calendar)
            }
        val datepicker = DatePickerDialog(
            requireContext(),
            date,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )

        datepicker.show()
    }

    private fun changeDateFormatAndSave(myCalendar: Calendar) {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US) //Example: 14.02.1999

        viewModel.enteredDate.value = sdf.format(myCalendar.time)
    }
}



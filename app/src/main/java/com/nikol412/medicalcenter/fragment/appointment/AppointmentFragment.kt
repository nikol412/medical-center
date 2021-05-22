package com.nikol412.medicalcenter.fragment.appointment

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

class AppointmentFragment : Fragment() {

    private val viewModel: AppointmentViewModel by viewModels()
    private lateinit var binding: FragmentAppointmentBinding

//    private lateinit var spinnerDepartmentsAdapter: ArrayAdapter<String>
//    private lateinit var doctorsAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)

        viewModel.navController = findNavController()
        binding.vm = viewModel

        binding.spinnerDepartments.onItemSelectedListener =
            viewModel.spinnerItemSelectedListenerDepartment
        binding.spinnerDoctors.onItemSelectedListener =
            viewModel.spinnerItemSelectedDoctors

        subscribeToVM()

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
}
//
//class SpinnerAdapter(context: Context, resourceId: Int): ArrayAdapter<Doctor>(context, resourceId) {
//
//    private var data = mutableListOf<Doctor>()
//    private val layoutInflater = LayoutInflater.from(context)
//
//    init {
//
//    }
//
//    fun setItems(newItems: List<Doctor>) {
//        this.addAll(newItems)
//        notifyDataSetChanged()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val item = getItem(position)
//        val view = layoutInflater.inflate()
//        return super.getView(position, convertView, parent)
//    }
//}

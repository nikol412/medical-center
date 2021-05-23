package com.nikol412.medicalcenter.fragment.prescribedTreatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentPrescribedTreatmentBinding
import com.nikol412.medicalcenter.fragment.prescribedTreatment.adapter.DrugsAdapter
import com.nikol412.medicalcenter.fragment.resultInspection.APPOINTMENT_ID

class PrescribedTreatmentFragment : Fragment() {

    private val viewModel: PrescribedTreatmentViewModel by viewModels()
    private lateinit var binding: FragmentPrescribedTreatmentBinding
    private val adapter: DrugsAdapter by lazy {
        DrugsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_prescribed_treatment,
            container,
            false
        )

        viewModel.navController = findNavController()
        binding.vm = viewModel
        binding.recyclerViewDrugs.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.drugs.observe(viewLifecycleOwner, Observer { drugsList ->
            adapter.setItems(drugsList)
        })

        arguments?.getInt(APPOINTMENT_ID)?.let { id ->
            viewModel.fetchDrugsForAppointment(id)
        }
    }

}
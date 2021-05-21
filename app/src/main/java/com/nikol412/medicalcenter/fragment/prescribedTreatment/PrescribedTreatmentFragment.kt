package com.nikol412.medicalcenter.fragment.prescribedTreatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentPrescribedTreatmentBinding

class PrescribedTreatmentFragment : Fragment() {

    private val viewModel: PrescribedTreatmentViewModel by viewModels()
    private lateinit var binding: FragmentPrescribedTreatmentBinding

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
        return binding.root
    }

}
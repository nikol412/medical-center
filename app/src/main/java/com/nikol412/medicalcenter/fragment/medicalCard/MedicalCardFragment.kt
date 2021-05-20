package com.nikol412.medicalcenter.fragment.medicalCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentMedicalCardBinding

class MedicalCardFragment : Fragment() {

    private val viewModel: MedicalCardViewModel by viewModels()
    private lateinit var binding: FragmentMedicalCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medical_card, container, false)
        viewModel.navController = findNavController()
        binding.vm = viewModel
        return binding.root
    }
}

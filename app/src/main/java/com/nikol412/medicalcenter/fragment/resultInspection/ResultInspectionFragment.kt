package com.nikol412.medicalcenter.fragment.resultInspection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentResultInspectionBinding

class ResultInspectionFragment : Fragment() {

    private val viewModel: ResultInspectionViewModel by viewModels()
    private lateinit var binding: FragmentResultInspectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_result_inspection, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        arguments?.getInt(APPOINTMENT_ID)?.let { id ->
            viewModel.fetchInspectionResult(id)
        }

        return binding.root
    }

}

const val APPOINTMENT_ID = "appointmentId"
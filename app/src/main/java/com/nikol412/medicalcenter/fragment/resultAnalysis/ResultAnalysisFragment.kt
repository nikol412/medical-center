package com.nikol412.medicalcenter.fragment.resultAnalysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentResultAnalysisBinding
import com.nikol412.medicalcenter.fragment.resultInspection.APPOINTMENT_ID

class ResultAnalysisFragment : Fragment() {

    private val viewModel: ResultAnalysisViewModel by viewModels()
    private lateinit var binding: FragmentResultAnalysisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getInt(APPOINTMENT_ID)?.let { id ->
            viewModel.fetchAnalysisResult(id)
        }

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_result_analysis, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}

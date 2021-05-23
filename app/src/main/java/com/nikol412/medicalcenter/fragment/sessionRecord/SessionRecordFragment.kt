package com.nikol412.medicalcenter.fragment.sessionRecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentSessionRecordBinding
import com.nikol412.medicalcenter.fragment.resultInspection.APPOINTMENT_ID

class SessionRecordFragment : Fragment() {

    private val viewModel: SessionRecordViewModel by viewModels()
    private lateinit var binding: FragmentSessionRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.appointmentId = arguments?.getInt(APPOINTMENT_ID)

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_session_record, container, false)

        viewModel.navController = findNavController()
        binding.vm = viewModel

        return binding.root
    }
}

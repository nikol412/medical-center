package com.nikol412.medicalcenter.fragment.certificate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikol412.medicalcenter.R
import com.nikol412.medicalcenter.databinding.FragmentSessionCertificateBinding

class SessionCertificateFragment : Fragment() {
    private val viewModel: SessionCertificateViewModel by viewModels()
    private lateinit var binding: FragmentSessionCertificateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_session_certificate,
            container,
            false
        )

        viewModel.navController = findNavController()
        binding.vm = viewModel
        return binding.root
    }
}
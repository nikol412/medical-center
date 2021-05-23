package com.nikol412.medicalcenter.fragment.medicalCard

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
import com.nikol412.medicalcenter.databinding.FragmentMedicalCardBinding
import com.nikol412.medicalcenter.fragment.medicalCard.adapter.MedicalCardsAdapter
import com.nikol412.medicalcenter.fragment.medicalCard.adapter.onMedicalCardClickListener

class MedicalCardFragment : Fragment() {

    private val viewModel: MedicalCardViewModel by viewModels()
    private lateinit var binding: FragmentMedicalCardBinding

    private val adapter: MedicalCardsAdapter by lazy {
        MedicalCardsAdapter(object: onMedicalCardClickListener {
            override fun onClick(id: Int) {
                // TODO: 23.05.2021 implement navigation to appointment record
                id
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medical_card, container, false)
        viewModel.navController = findNavController()
        binding.vm = viewModel

        binding.recyclerViewAppointments.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.appointments.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}

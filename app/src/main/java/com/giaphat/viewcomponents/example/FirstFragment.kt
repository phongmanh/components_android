package com.giaphat.viewcomponents.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.giaphat.viewcomponents.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ExampleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        _binding?.viewModel = viewModel
        _binding?.lifecycleOwner = viewLifecycleOwner
        viewModel.loadListJob()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            viewModel.text.postValue("Text changed")
        }

        viewModel.text.observe(viewLifecycleOwner) {
            println(it)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
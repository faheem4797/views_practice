package com.example.viewspractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewspractice.databinding.FragmentTermsBinding

class TermsFragment : Fragment() {

    private lateinit var binding: FragmentTermsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTermsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}
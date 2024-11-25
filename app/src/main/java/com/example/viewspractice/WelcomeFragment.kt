package com.example.viewspractice

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.viewspractice.databinding.FragmentEmailBinding
import com.example.viewspractice.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        val name = requireArguments().getString("name_input")
        val email = requireArguments().getString("email_input")

        binding.tvWelcomeName.text = name
        binding.tvWelcomeEmail.text = email
        binding.btnTermsWelcome.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment)
        }
        return binding.root
    }

}
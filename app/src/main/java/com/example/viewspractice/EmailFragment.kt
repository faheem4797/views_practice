package com.example.viewspractice

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.viewspractice.databinding.FragmentEmailBinding
import com.example.viewspractice.databinding.FragmentNameBinding

class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEmailBinding.inflate(layoutInflater, container, false)
        val name = requireArguments().getString("name_input")

        binding.btnSubmit.setOnClickListener {

            if(Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()){

                val bundle = bundleOf("email_input" to binding.etEmail.text.toString(), "name_input" to name )
                it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle)
            }else{
                Toast.makeText(activity, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}
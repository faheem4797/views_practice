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
import com.example.viewspractice.databinding.FragmentNameBinding

class NameFragment : Fragment() {

    private lateinit var binding: FragmentNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNameBinding.inflate(layoutInflater, container, false)
        binding.btnNext.setOnClickListener {
            if(!TextUtils.isEmpty(binding.etName.text.toString())){
                val bundle = bundleOf("name_input" to binding.etName.text.toString())
                it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment,bundle)

            }else{
                Toast.makeText(activity, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}
package edu.uca.innovatech.investigacionauthxmlyjson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import edu.uca.innovatech.investigacionauthxmlyjson.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    //el view binding
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnXML.setOnClickListener(){
            it.findNavController().navigate(R.id.action_menuFragment_to_XMLFragment)
        }
        binding.btnJSON.setOnClickListener(){
            it.findNavController().navigate(R.id.action_menuFragment_to_JSONFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
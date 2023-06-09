package edu.uca.innovatech.investigacionauthxmlyjson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import edu.uca.innovatech.investigacionauthxmlyjson.databinding.FragmentLoginMethodBinding
import java.net.Socket
import kotlin.concurrent.thread

class LoginMethodFragment : Fragment() {

    //el view binding
    private var _binding: FragmentLoginMethodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSQLLogin.setOnClickListener(){
            it.findNavController().navigate(R.id.action_loginMethodFragment_to_SQLLoginFragment)
        }
        binding.btnFirebaseLogin.setOnClickListener(){
            it.findNavController().navigate(R.id.action_loginMethodFragment_to_firebaseLoginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
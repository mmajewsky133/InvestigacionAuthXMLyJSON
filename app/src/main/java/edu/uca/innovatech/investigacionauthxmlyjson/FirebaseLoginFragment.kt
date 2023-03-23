package edu.uca.innovatech.investigacionauthxmlyjson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import edu.uca.innovatech.investigacionauthxmlyjson.databinding.FragmentFirebaseLoginBinding

class FirebaseLoginFragment : Fragment() {

    //el view binding
    private var _binding: FragmentFirebaseLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirebaseLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.btnLogin.setOnClickListener {
            val user = binding.etUsername.text.toString()
            val pw = binding.etPassword.text.toString()

            if (user.isNotEmpty() && pw.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(user, pw).addOnCompleteListener{
                    if (it.isSuccessful) {
                        Toast.makeText(context,"Login correcto...",Toast.LENGTH_LONG).show()
                        navigateToMenu()
                    } else {
                        Toast.makeText(context,"Credenciales incorrectas...",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun navigateToMenu() {
        this.findNavController().navigate(R.id.action_firebaseLoginFragment_to_menuFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
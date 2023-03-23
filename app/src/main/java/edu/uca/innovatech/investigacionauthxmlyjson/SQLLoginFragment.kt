package edu.uca.innovatech.investigacionauthxmlyjson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import edu.uca.innovatech.investigacionauthxmlyjson.databinding.FragmentSQLLoginBinding
import edu.uca.innovatech.investigacionauthxmlyjson.network.ConnectionSQL
import java.sql.PreparedStatement
import java.sql.SQLException

class SQLLoginFragment : Fragment() {

    //el view binding
    private var _binding: FragmentSQLLoginBinding? = null
    private val binding get() = _binding!!

    //Instancia de la clase Conexion
    private var connectionSQL = ConnectionSQL()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSQLLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val loginSuccessful = verifyCredentials(username, password)

            if (loginSuccessful) {
                it.findNavController().navigate(R.id.action_SQLLoginFragment_to_menuFragment)
            } else {
                Toast.makeText(context, "Credenciales incorrectas...", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verifyCredentials(user: String, pw: String): Boolean {
        try {
            val query: PreparedStatement =
                connectionSQL.dbConn()?.prepareStatement("SELECT * FROM users WHERE username=?")!!
                query.setString(1, user)
            val rs = query.executeQuery()

            if (rs.next()){
               if ((rs.getString(2) == user) && (rs.getString(3) == pw)){
                   return true
               }
            }

        } catch (ex: SQLException) {
            println("false por exception")
            return false
        }
        println("false generico")
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
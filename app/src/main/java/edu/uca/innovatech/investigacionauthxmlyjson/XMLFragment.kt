package edu.uca.innovatech.investigacionauthxmlyjson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import edu.uca.innovatech.investigacionauthxmlyjson.databinding.FragmentXMLBinding
import javax.xml.parsers.DocumentBuilderFactory

class XMLFragment : Fragment() {

    //el view binding
    private var _binding: FragmentXMLBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentXMLBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLoad.setOnClickListener {
            //Manda a request usando la url ingresada
            Toast.makeText(context, "Requesting...", Toast.LENGTH_SHORT).show()
            cargarDatosDesdeUrl(binding.editTextUrl.text.toString())
        }
    }
    private fun cargarDatosDesdeUrl(url: String) {
        //Crea una una solicitud usando Volley
        val queue = Volley.newRequestQueue(context)
        Toast.makeText(context, "Queue Requested...", Toast.LENGTH_SHORT).show()

        //Crea una solicitud GET para la URL proporcionada
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response -> //Devuelve la response del url
                try {
                    //Procesa la response como un objeto XML y asigna los datos a variables
                    val factory = DocumentBuilderFactory.newInstance()
                    val builder = factory.newDocumentBuilder()
                    val doc = builder.parse(response.byteInputStream())
                    val author = doc.getElementsByTagName("author").item(0).textContent
                    val category = doc.getElementsByTagName("category").item(0).textContent
                    val title = doc.getElementsByTagName("title").item(0).textContent
                    val year = doc.getElementsByTagName("year").item(0).textContent.toInt()
                    val price = doc.getElementsByTagName("price").item(0).textContent.toFloat()
                    val datos = "Nombre: $author \nCategoria: $category \nTitulo: $title \nAño: $year \nPrecio: $price"
                    binding.textViewData.text = datos
                    Toast.makeText(context, "Done...", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Error en la solicitud...", Toast.LENGTH_LONG).show()
                }
            }) { error -> println(error.message) }

        //Ejecuta el request
        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
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
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

class XMLFragment : Fragment() {
    private var text: String? = null

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
                    try {
                        var datos = ""
                        val factory = XmlPullParserFactory.newInstance()
                        factory.isNamespaceAware = true
                        val parser = factory.newPullParser()
                        parser.setInput(response.reader())
                        var eventType = parser.eventType
                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            val tagname = parser.name
                            when (eventType) {
                                XmlPullParser.TEXT -> text = parser.text
                                XmlPullParser.END_TAG -> if (tagname.equals("book", ignoreCase = true)) {
                                    datos += "Libro: \n"
                                } else if (tagname.equals("title", ignoreCase = true)) {
                                    datos += "Titulo: $text\n"
                                } else if (tagname.equals("author", ignoreCase = true)) {
                                    datos += "Autor: $text\n"
                                } else if (tagname.equals("year", ignoreCase = true)) {
                                    datos += "Año: $text\n"
                                } else if (tagname.equals("price", ignoreCase = true)) {
                                    datos += "Precio: $text\n\n"
                                }
                                else -> {
                                }
                            }
                            eventType = parser.next()
                        }
                        binding.textViewData.text = datos

                    } catch (e: XmlPullParserException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Toast.makeText(context, "Done...", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Error en la solicitud...", Toast.LENGTH_LONG).show()
                }
            }) { error -> println(error.message) }

        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
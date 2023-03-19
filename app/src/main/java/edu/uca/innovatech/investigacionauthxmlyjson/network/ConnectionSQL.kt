package edu.uca.innovatech.investigacionauthxmlyjson.network

import android.os.StrictMode
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionSQL {

    private val address = "192.168.1.7:1433"
    private val db = "Autenticacion"
    private val username = "sa"
    private val password = "Usuario123.#"

    fun dbConn(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var conn : Connection? = null
        val connString : String

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString =
                "jdbc:jtds:sqlserver://$address;databaseName=$db;user=$username;password=$password"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){
            println("Error: ${ex.message!!}")
        }catch (ex1: ClassNotFoundException){
            println("Error: ${ex1.message!!}")
        }catch (ex2: Exception){
            println("Error: ${ex2.message!!}")
        }
        return conn
    }
}
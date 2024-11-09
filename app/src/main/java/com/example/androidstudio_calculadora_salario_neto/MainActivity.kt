package com.example.androidstudio_calculadora_salario_neto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Añadimos funcionalidad al botón para recoger los campos que se han ido rellenando
        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.enviarDatos).setOnClickListener{
            val salario = findViewById<EditText>(R.id.salarioBrutoRespuesta)
            val pagas = findViewById<EditText>(R.id.numPagasRespuesta)
            val edad = findViewById<EditText>(R.id.edadRespuesta)
            val grupo = findViewById<EditText>(R.id.grupoRespuesta)
            val discapacidad = findViewById<EditText>(R.id.discapacidadRespuesta)
            val estado = findViewById<EditText>(R.id.estadoRespuesta)
            val hijos = findViewById<EditText>(R.id.hijosRespuesta)


            // Comprobamos que no quede ninguno sin rellenar
            if(salario.text.isEmpty() || pagas.text.isEmpty() || edad.text.isEmpty()
                || grupo.text.isEmpty() || discapacidad.text.isEmpty() || estado.text.isEmpty()
                || hijos.text.isEmpty()){

                salario.setHintTextColor(Color.RED)
                salario.setHint("Inserte salario")

                pagas.setHintTextColor(Color.RED)
                pagas.setHint("Inserte nº de pagas")

                edad.setHintTextColor(Color.RED)
                edad.setHint("Inserte edad")

                grupo.setHintTextColor(Color.RED)
                grupo.setHint("Inserte grupo")

                discapacidad.setHintTextColor(Color.RED)
                discapacidad.setHint("Inserte porcentaje")

                estado.setHintTextColor(Color.RED)
                estado.setHint("Inserte estado civil")

                hijos.setHintTextColor(Color.RED)
                hijos.setHint("Inserte nº de hijos")
            } else {
                // Una vez rellenados, se prepara el cambio de vista
                val intent = Intent(this, Calculadora::class.java)

                // Preparamos los datos para que vayan a la siguiente activity con el tipo de dato necesario
                intent.putExtra("salario", salario.text.toString().toDouble())
                intent.putExtra("pagas", pagas.text.toString().toInt())
                intent.putExtra("edad", edad.text.toString().toInt())
                intent.putExtra("grupo", grupo.text.toString())
                intent.putExtra("discapacidad", discapacidad.text.toString().toInt())
                intent.putExtra("estado", estado.text.toString())
                intent.putExtra("hijos", hijos.text.toString().toInt())

                // Saltamos a la otra activity
                startActivity(intent)
            }
        }
    }
}
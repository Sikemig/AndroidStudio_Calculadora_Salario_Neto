package com.example.androidstudio_calculadora_salario_neto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    // Creamos variables privadas para recoger los elementos visuales, con inicializacion tardía (lazy)
    private lateinit var  salarioBrutoRespuesta: EditText

    private lateinit var  pagasMenos: ImageButton
    private lateinit var  pagasMas: ImageButton
    private lateinit var  numPagasRespuesta: TextView

    private lateinit var  edadMenos: ImageButton
    private lateinit var  edadMas: ImageButton
    private lateinit var  edadRespuesta: TextView

    private lateinit var  grupoRespuesta: EditText

    private lateinit var  discapacidadMenos: ImageButton
    private lateinit var  discapacidadMas: ImageButton
    private lateinit var  discapacidadRespuesta: TextView

    private lateinit var  estadoRespuesta: EditText

    private lateinit var  hijosMenos: ImageButton
    private lateinit var  hijosMas: ImageButton
    private lateinit var  hijosRespuesta: TextView

    private lateinit var  enviarDatos: Button

    // Atributos que voy a utilizar con botones
    private var currentEdad : Int = 18
    private var currentPagas : Int = 12
    private var currentDiscapacidad : Int = 0
    private var currentHijos : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Para iniciar componentes visuales
        initComponents()

        // Para iniciar los listener de los eventos
        initListeners()

        // Para iniciar configuraciones de los componentes visuales
        initUI()
    }


    private fun initListeners(){
        this.pagasMenos.setOnClickListener {
            this.currentPagas-=1
            setPagas()
        }
        this.pagasMas.setOnClickListener {
            this.currentPagas+=1
            setPagas()
        }

        this.edadMenos.setOnClickListener {
            this.currentEdad-=1
            setEdad()
        }
        this.edadMas.setOnClickListener {
            this.currentEdad+=1
            setEdad()
        }

        this.discapacidadMenos.setOnClickListener {
            this.currentDiscapacidad-=5
            setDiscapacidad()
        }
        this.discapacidadMas.setOnClickListener {
            this.currentDiscapacidad+=5
            setDiscapacidad()
        }

        this.hijosMenos.setOnClickListener {
            this.currentHijos-=1
            setHijos()
        }
        this.hijosMas.setOnClickListener {
            this.currentHijos+=1
            setHijos()
        }

        this.enviarDatos.setOnClickListener{
            // Navegacion
            navigateToResult()
        }
    }



    private fun initUI() {
        this.setPagas()
        this.setEdad()
        this.setDiscapacidad()
        this.setHijos()
    }

    private fun setPagas() {
        this.numPagasRespuesta.text = this.currentPagas.toString()
    }

    private fun setEdad() {
        this.edadRespuesta.text = this.currentEdad.toString()
    }

    private fun setDiscapacidad() {
        this.discapacidadRespuesta.text = this.currentDiscapacidad.toString()
    }

    private fun setHijos() {
        this.hijosRespuesta.text = this.currentHijos.toString()
    }


    private fun initComponents(){
        this.salarioBrutoRespuesta = findViewById(R.id.salarioBrutoRespuesta)

        this.pagasMenos = findViewById(R.id.pagasMenos)
        this.pagasMas = findViewById(R.id.pagasMas)
        this.numPagasRespuesta= findViewById(R.id.numPagasRespuesta)

        this.edadMenos=findViewById(R.id.edadMenos)
        this.edadMas=findViewById(R.id.edadMas)
        this.edadRespuesta=findViewById(R.id.edadRespuesta)

        this.grupoRespuesta=findViewById(R.id.grupoRespuesta)

        this.discapacidadMenos=findViewById(R.id.discapacidadMenos)
        this.discapacidadMas=findViewById(R.id.discapacidadMas)
        this.discapacidadRespuesta=findViewById(R.id.discapacidadRespuesta)

        this.estadoRespuesta=findViewById(R.id.estadoRespuesta)

        this.hijosMenos=findViewById(R.id.hijosMenos)
        this.hijosMas=findViewById(R.id.hijosMas)
        this.hijosRespuesta=findViewById(R.id.hijosRespuesta)

        this.enviarDatos=findViewById(R.id.enviarDatos)
    }


    private fun navigateToResult() {
        if (salarioBrutoRespuesta.text.isEmpty() || numPagasRespuesta.text.isEmpty() || edadRespuesta.text.isEmpty()
            || grupoRespuesta.text.isEmpty() || discapacidadRespuesta.text.isEmpty() || estadoRespuesta.text.isEmpty()
            || hijosRespuesta.text.isEmpty()
        ) {

            salarioBrutoRespuesta.setHintTextColor(Color.RED)
            salarioBrutoRespuesta.setHint("Inserte salario")

            numPagasRespuesta.setHintTextColor(Color.RED)
            numPagasRespuesta.setHint("Inserte nº de pagas")

            edadRespuesta.setHintTextColor(Color.RED)
            edadRespuesta.setHint("Inserte edad")

            grupoRespuesta.setHintTextColor(Color.RED)
            grupoRespuesta.setHint("Inserte grupo")

            discapacidadRespuesta.setHintTextColor(Color.RED)
            discapacidadRespuesta.setHint("Inserte porcentaje")

            estadoRespuesta.setHintTextColor(Color.RED)
            estadoRespuesta.setHint("Inserte estado civil")

            hijosRespuesta.setHintTextColor(Color.RED)
            hijosRespuesta.setHint("Inserte nº de hijos")

        } else {

            val intent = Intent(this, Calculadora::class.java)
            // enviamos a la otra activity solo lo que se va a utilizar
            intent.putExtra("salario", salarioBrutoRespuesta.text.toString().toDouble())
            intent.putExtra("pagas", currentPagas)
            intent.putExtra("discapacidad", currentDiscapacidad)
            intent.putExtra("hijos", currentHijos)

            this.startActivity(intent)
        }
    }
}
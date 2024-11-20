package com.example.androidstudio_calculadora_salario_neto

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.time.times

class Calculadora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recogemos los valores de la otra activity
        val salario: Double = intent.extras?.getDouble("salario")?:0.0
        val pagas: Int = intent.extras?.getInt("pagas")?:0
        val discapacidad: Int = intent.extras?.getInt("discapacidad")?:0
        val hijos: Int = intent.extras?.getInt("hijos")?:0

        // Creamos los elementos que contendrán los datos al usuario segun cada textview
        val salarioBruto = findViewById<TextView>(R.id.salarioBruto)
        val irpf = findViewById<TextView>(R.id.retencionIRPF)
        val deducciones = findViewById<TextView>(R.id.deducciones)
        val salarioNeto = findViewById<TextView>(R.id.salarioNeto)


        // Ahora vamos mostrando los datos que nos interesan en cada apartado de vista de resultados

        // Primero vamos a mostrar el salario bruto mensual teniendo en cuenta el número de pagas
        val salarioBrutoMes = String.format("%.2f", salario/pagas).toDouble() // Para redondear a 2 cifras
        salarioBruto.text ="El salario bruto mensual del trabajador es: " + salarioBrutoMes

        // Mostramos la retención IRPF según el salario bruto
        var deduccionIRPF : Int = 0
        when{
            salario < 12450 -> deduccionIRPF = 19
            salario in 12451.0..20199.0 -> deduccionIRPF =24
            salario in 20200.0 .. 35199.0 -> deduccionIRPF = 30
            salario in 35200.0 .. 59999.0 -> deduccionIRPF = 37
            salario in 60000.0..299999.0 -> deduccionIRPF = 45
            else -> deduccionIRPF = 47
        }
        irpf.text = "$deduccionIRPF% de IRPF"

        // Ahora las deducciones aplicadas, vamos a aplicar deducciones por familia numerosa y por discapacidad
        var sumaDeduccion: Int = 0

        if(hijos >= 3)
            sumaDeduccion+=5

        if(discapacidad >= 65){
            sumaDeduccion+=10
        } else if (discapacidad>35 && discapacidad<65){
            sumaDeduccion+=5
        }

        deducciones.text = "$sumaDeduccion % de deducción"

        // Finalmente procedemos a calcular el salario neto mensual
            // Tenemos que ver que % final de IRPF se aplica segun las deducciones

            // Pasamos el IRPF a un número decimal
            val irpfPorcentaje : Double = (deduccionIRPF.toDouble()/100)
            // Aplicamos las deducciones en número decimal al IRPF en número decimal
            val irpfDeducido : Double = irpfPorcentaje*(sumaDeduccion.toDouble()/100)
            /* Una vez averiguado el % que se va a descontar del IRPF, tenemos que RESTARLO al irpf total,
            se usa el String.format para redondearlo en 2 cifras y luego hay que volver a pasarlo a Double*/
            val irpfFinal : Double = String.format("%.2f", (irpfPorcentaje-irpfDeducido)*100).toDouble()

        // Una vez calculado el % de impuestos que se va a descontar, se aplica al salario bruto
        val salarioDescontar : Double = salarioBrutoMes * (irpfFinal/100)
        val salarioNetoMes : Double = salarioBrutoMes - salarioDescontar

        val salarioNetoMesRedondear : Double = String.format("%.2f", salarioNetoMes).toDouble()

        salarioNeto.text="El salario neto mensual del trabajador es $salarioNetoMesRedondear"
    }
}
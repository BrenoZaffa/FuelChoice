package com.example.fuelchoice

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelchoice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btListaCombustiveis1.setOnClickListener() {
            val intent = Intent(this, ListagemCombustiveis::class.java)
            getResult1.launch(intent)
        }

        binding.btListaCombustiveis2.setOnClickListener() {
            val intent = Intent(this, ListagemCombustiveis::class.java)
            getResult2.launch(intent)
        }

        binding.btCalcular.setOnClickListener() { calcularResultado() }
    }

    private fun calcularResultado() {
        binding.tvResultado.text = ""
        if(verificarCamposVazios()) return

        val consumo1 = binding.etConsumo1.text.toString().toDouble()
        val consumo2 = binding.etConsumo2.text.toString().toDouble()
        val valor1 = binding.etValor1.text.toString().toDouble()
        val valor2 = binding.etValor2.text.toString().toDouble()

        val resultado1 = valor1 / consumo1
        val resultado2 = valor2 / consumo2

        if(resultado1 < resultado2) {
            binding.tvResultado.text = getString(R.string.combustivel_1_mais_economico)
        } else if(resultado1 > resultado2) {
            binding.tvResultado.text = getString(R.string.combustivel_2_mais_economico)
        } else
            binding.tvResultado.text = getString(R.string.os_combustiveis_sao_igualmente_eficientes)
    }

    private val getResult1 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val cod = result.data?.getDoubleExtra("Consumo", 0.0)
            binding.etConsumo1.setText(cod.toString())
            binding.etConsumo1.error = null
        }
    }

    private val getResult2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val cod = result.data?.getDoubleExtra("Consumo", 0.0)
            binding.etConsumo2.setText(cod.toString())
            binding.etConsumo2.error = null
        }
    }

    private fun verificarCamposVazios(): Boolean {
        binding.etConsumo1.error = if(binding.etConsumo1.text.toString().isEmpty()) getString(R.string.campo_obrigatorio) else null
        binding.etConsumo2.error = if(binding.etConsumo2.text.toString().isEmpty()) getString(R.string.campo_obrigatorio) else null
        binding.etValor1.error = if(binding.etValor1.text.toString().isEmpty()) getString(R.string.campo_obrigatorio) else null
        binding.etValor2.error = if(binding.etValor2.text.toString().isEmpty()) getString(R.string.campo_obrigatorio) else null

        return binding.etConsumo1.text.toString().isEmpty() ||
                binding.etConsumo2.text.toString().isEmpty() ||
                binding.etValor1.text.toString().isEmpty() ||
                binding.etValor2.text.toString().isEmpty()
    }
}
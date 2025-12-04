package com.example.fuelchoice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fuelchoice.databinding.ActivityListagemCombustiveisBinding

class ListagemCombustiveis : AppCompatActivity() {

    private lateinit var binding : ActivityListagemCombustiveisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListagemCombustiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ListagemCombustiveis)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ListagemCombustiveis.setOnItemClickListener{ parent, view, position, id ->
            val cod = position + 1

            var consumo = 0.0
            when (cod) {
                1 -> consumo = 10.0
                2 -> consumo = 7.0
            }

            intent.putExtra("Consumo", consumo)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
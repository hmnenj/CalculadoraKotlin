package com.example.calculadora

import android.widget.Button
import android.annotation.SuppressLint
import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   //binding
    private lateinit var binding: ActivityMainBinding

    //variaveis
    private var primeiraNumero = ""
    private var numeroAtual = ""
    private var operadorAtual = ""
    private  var resultado = ""
    @SuppressLint("SetText18")
    //criacao de funcao android studio
    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //limitar tela

    }
}
package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.view.WindowManager
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   //binding
    private lateinit var binding : ActivityMainBinding

    //variaveis
    private var primeiroNumero = ""
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
    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //views
        binding.apply {
            //obter todos os botoes
            layoutMain.children.filterIsInstance<Button>().forEach{ botao ->
                //clique dos botoes
                botao.setOnClickListener{
                    //obter o texto do botao clicado
                    val textoDoBotao = botao.text.toString()
                    when{
                        textoDoBotao.matches(Regex("[0-9]")) -> {
                            if (operadorAtual.isEmpty()){
                                primeiroNumero += textoDoBotao
                                tvResult.text = primeiroNumero
                            } else{
                                numeroAtual += textoDoBotao
                                tvResult.text = numeroAtual
                            }
                        }
                        textoDoBotao.matches(Regex("[+\\-*/]")) ->
                    }
                }
            }
        }
    }
}
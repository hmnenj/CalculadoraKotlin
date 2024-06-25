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
                        textoDoBotao.matches(Regex("[+\\-*/]")) -> {
                            //limpa o numero atual
                            numeroAtual = ""
                            //verifica se ha algum texto no textview tvresult
                            if (tvResult.text.toString().isNotEmpty()){
                                //atualiza o operador atual e mostra '0' na tela
                                operadorAtual = textoDoBotao
                                tvResult.text = "0"
                            }
                        }
                        //se o texto do botao é '=' realizar calculo da expressao
                        textoDoBotao == "=" -> {
                            if(numeroAtual.isNotEmpty() && operadorAtual.isNotEmpty()){
                                //exibe a formula completa na textview tvformula
                                tvFormula.text = "$primeiroNumero$operadorAtual$numeroAtual"
                                //avalia a expressao e armazena o resultado
                                resultado = avaliarExpressao(primeiroNumero, numeroAtual, operadorAtual)
                                //atualiza o primeiro numero com o resultado
                                primeiroNumero = resultado
                                //mostra o resultado na tela(textview tv result)
                                tvResult.text = resultado
                            }
                        }
                        //se o texto do botao é '.' adiciona ponto decimal ao numero
                        textoDoBotao == "." ->{
                            if (operadorAtual.isEmpty()){
                                //se nao ha operador verifica se o primeiro numero ja é decimal
                                if (!primeiroNumero.contains(".")){
                                    //adiciona ponto decimal ao primeiro numero
                                    if (primeiroNumero.isEmpty()) numeroAtual += "0$textoDoBotao"
                                    else primeiroNumero += textoDoBotao
                                    //atualiza o texto na tela (textview tvresult)
                                    tvResult.text = primeiroNumero
                                }
                            }else {
                                // se ha operador, verifica se o numero atual ja contem ponto decimal
                                if (!numeroAtual.contains(".")){
                                    //adiciona ponto decimal ao numero atual
                                    if (numeroAtual.isEmpty()) numeroAtual += "0$textoDoBotao"
                                    else numeroAtual += textoDoBotao
                                    //atualiza o texto na tela (textview tvresult)
                                    tvResult.text = numeroAtual
                                }
                            }
                        }
                        //se o texto do botao for 'c' limpa todos os valores e reinicie
                        textoDoBotao == "C" -> {
                            numeroAtual = ""
                            primeiroNumero = ""
                            operadorAtual = ""
                            //mostra '0' na tela (textview tvresult)
                            tvResult.text = "0"
                            //limpa a formula na tela (textview tvformula)
                            tvFormula.text = ""
                        }
                    }
                }
            }
        }
    }
    //funcao para avaliar a expressao matematica e retornar o resultado como string
    private fun avaliarExpressao(primeiroNumero: String, segundoNumero: String, operador: String): String{
        //converte os numeros de string para double
        val num1 = primeiroNumero.toDouble()
        val num2 = segundoNumero.toDouble()
        //realiza a oprecao de acordo com o operador selecionado
        return when (operador) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> (num1 / num2).toString()
            else -> ""
        }
    }
}
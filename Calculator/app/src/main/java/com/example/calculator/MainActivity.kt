package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var textBox:TextView? = null
    private var lastNumeric:Boolean = false
    private var lastDecimal:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textBox = findViewById(R.id.textBox)
    }

    fun onDigit(view: View){

        //We are using view as button here since view does not have text property
        textBox?.append((view as Button).text)
        lastNumeric = true
        lastDecimal = false
    }

    fun onClear(view:View){
        textBox?.text = ""
    }

    fun onBackspace(view:View){
        var result = textBox?.text.toString()
        result = result.substring(0,result.length-1)
        textBox?.text = result
    }

    fun onDecimal(view:View){
        if(lastNumeric && !lastDecimal){
            textBox?.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun onOperator(view:View){

        var result1 = textBox?.text.toString()
        result1 = result1.substring(result1.length-1,result1.length)
        if(result1 == "+" || result1 == "-" || result1 == "X" || result1 == "/"
            || result1 == "%" || result1 == "^" || result1 == "."){
            lastNumeric = false
            if(result1 == ".")  lastDecimal = true
        }

        textBox?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                textBox?.append((view as Button).text)
                lastNumeric = false
                lastDecimal = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view:View){

        var textValue = textBox?.text.toString()
        var prefix = ""

        if(lastNumeric){

            if(textValue.startsWith("-")){
                prefix = "-"
                textValue = textValue.substring(1)
            }

            try{

                if(textValue.contains("-")){

                    val splitValue = textValue.split("-")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) one = prefix + one

                    textBox?.text = removeDotZero((one.toDouble()-two.toDouble()).toString())

                }
                else if(textValue.contains("+")){

                    val splitValue = textValue.split("+")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) one = prefix + one

                    textBox?.text = removeDotZero((one.toDouble()+two.toDouble()).toString())

                }
                else if(textValue.contains("X")){

                    val splitValue = textValue.split("X")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(prefix.isNotEmpty()) one = prefix + one

                    textBox?.text = (one.toDouble()*two.toDouble()).toString()

                }
                else if(textValue.contains("/")){

                    val splitValue = textValue.split("/")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    val temp = two.toInt()
                    if(temp == 0)   textBox?.text = "Infinity"
                    else{

                        if(prefix.isNotEmpty()) one = prefix + one

                        textBox?.text = (one.toDouble()/two.toDouble()).toString()
                    }
                }
                else if(textValue.contains("%")){

                    val splitValue = textValue.split("%")

                    var one = splitValue[0]
                    val two = splitValue[1]

                    val temp = two.toInt()
                    if(temp == 0)   textBox?.text = "Infinity"
                    else{
                        if(prefix.isNotEmpty()) one = prefix + one

                        textBox?.text = (one.toInt()%two.toInt()).toString()
                    }
                }
                else{

                    val splitValue = textValue.split("^")

                    var one = splitValue[0]
                    val two = splitValue[1]
                    val temp = two.toInt()
                    if(temp == 0)   textBox?.text = "1"
                    else{

                        if(prefix.isNotEmpty()) one = prefix + one

                        var result = one.toInt()

                        for(i in 1 until two.toInt()){
                            result *= one.toInt()
                        }
                        textBox?.text = (result).toString()
                    }
                }

            }
            catch(e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    private fun removeDotZero(value : String) : String{

        var result = value
        if(value.contains(".0"))
            result = value.substring(0,value.length-2)
        return result
    }

    private fun isOperatorAdded(value:String) : Boolean{

        return if(value.startsWith("-")){
            false
        }
        else{
            value.startsWith("+")
                    ||value.startsWith("-")
                    ||value.startsWith("/")
                    ||value.startsWith("*")
                    ||value.startsWith("^")
                    ||value.startsWith("%")
        }
    }
}



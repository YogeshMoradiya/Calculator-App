package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    lateinit var operator:String
    lateinit var main_textview : TextView
    lateinit var secound : TextView
    var tempNum : Double = 0.0
    var firstnum :Double=0.0
    var secondnum :Double=0.0
    var array = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secound=findViewById(R.id.ftext)
        main_textview =findViewById(R.id.text)

        val buttons = listOf<Button>(
            findViewById(R.id.one),
            findViewById(R.id.two),
            findViewById(R.id.three),
            findViewById(R.id.four),
            findViewById(R.id.five),
            findViewById(R.id.six),
            findViewById(R.id.seven),
            findViewById(R.id.eight),
            findViewById(R.id.nine),
            findViewById(R.id.zero),
            findViewById(R.id.point),
            findViewById(R.id.clear),
            findViewById(R.id.plus),
            findViewById(R.id.minus),
            findViewById(R.id.multiply),
            findViewById(R.id.div),
            findViewById(R.id.equals),
            findViewById(R.id.percent),
            findViewById(R.id.delet),
            findViewById(R.id.plusminus)
        )
        for (button in buttons){
            button.setOnClickListener {
                runcode(button.text.toString())
            }
        }
    }
    private fun runcode(input:String ) {
        when (input) {
            "+", "-", "*", "/" -> {
                try {
                    operator = input
                    tempNum = main_textview.text.toString().toDouble()
                    if(OperatorSign.plus)
                    {
                        firstnum=tempNum+firstnum
                        //Log.e("=====", "run-code:$firstnum ", )
                    }
                    else  if(OperatorSign.minus) {
                        firstnum = tempNum - firstnum
                    }else if(OperatorSign.multi){
                        firstnum=tempNum*firstnum
                    }else if(OperatorSign.equals("*")){
                        OperatorSign.multi=true
                        Log.e("===11==", "run-code:$firstnum ", )
                    }else if(OperatorSign.div){
                        firstnum=tempNum/firstnum
                    }else{
                        firstnum=tempNum
                    }


                    if(operator.equals("+"))
                    {
                        OperatorSign.plus = true
                    }else if(operator.equals("-")){
                        OperatorSign.minus=true
                    }else if(operator.equals("*")){
                        OperatorSign.multi=true
                    }else if(operator.equals("/")){
                        OperatorSign.div=true
                    }
                    main_textview.text = ""
                }
                catch ( i : NumberFormatException)
                {

                }
            }
            "=" -> {
                try {
                    secondnum = main_textview.text.toString().toDouble()
                    secound.text=firstnum.toString()+(operator)+secondnum.toString()
                    val result = when (operator) {
                        "+" ->
                        {
                            try {
                                firstnum + secondnum
                            }
                            catch ( i : NullPointerException)
                            {

                            }
                        }
                        "-"->{
                            try {
                                firstnum-secondnum
                            }
                            catch (i:NullPointerException){

                            }
                        }
                        "*" ->
                        {
                            try {
                                firstnum * secondnum
                            }
                            catch (i:NullPointerException){
                            }
                        }
                        "/" ->
                        {
                            try {
                                firstnum / secondnum
                            }
                            catch (i:NumberFormatException){

                            }
                        }
                        else -> {
                            0.0
                        }
                    }
                    main_textview.text = result.toString()
                    firstnum=0.0
                }
                catch (i:NumberFormatException){

                }
            }
            "AC" -> {
                firstnum = 0.0
                secondnum = 0.0
                main_textview.text = ""
                secound.text=""
                OperatorSign.plus = false
                OperatorSign.div =false
                OperatorSign.multi=false
                OperatorSign.minus=false
            }

            "%" -> {
                try {
                    firstnum = (main_textview.text.toString().toDouble()) / 100
                    main_textview.setText(firstnum.toString()+"%")
                }
                catch (i:NumberFormatException){

                }
            }
            "⌫" -> {
                try {
                    main_textview.setText(main_textview.text.substring(0, main_textview.text.length - 1))
                }
                catch (i:StringIndexOutOfBoundsException){

                }
            }
            "+/-" -> {
                if (main_textview.text.isEmpty()) {
                    main_textview.setText("-")
                } else if (main_textview.text.startsWith("-")) {
                    main_textview.setText(main_textview.text.substring(1))
                } else {
                    main_textview.setText("-" + main_textview.text)
                }
            }
            "." -> {
                if (1 > main_textview.text.indexOf(".")) {
                    if (main_textview.text.isEmpty()) {
                        main_textview.setText(main_textview.text.toString() + "0.")
                    } else {
                        main_textview.setText(main_textview.text.toString() + ".")
                    }
                }
            }
            "0" -> {
                if (main_textview.text.toString().isEmpty()) {
                    main_textview.setText("0")
                } else if (main_textview.text.equals("0")) {
                    main_textview.setText("0")
                } else if (main_textview.text.toString() != "0") {
                    main_textview.setText(main_textview.text.toString() + "0")
                }
            }
            else -> {
                main_textview.append(input)
            }
        }
    }
}
class OperatorSign
{
    companion object{
        var plus : Boolean = false
        var minus : Boolean = false
        var multi : Boolean = false
        var div : Boolean = false

    }
}

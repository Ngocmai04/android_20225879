package com.example.calculator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var currentInput = ""
    private var operator = ""
    private var operand1 = 0.0
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity) //

        resultText = findViewById(R.id.result)

        // Danh sách các nút
        val buttonIds = listOf(
            R.id.button13, R.id.button14, R.id.button15, // 1 2 3
            R.id.button9, R.id.button10, R.id.button11,  // 4 5 6
            R.id.button5, R.id.button6, R.id.button7,    // 7 8 9
            R.id.button18,                               // 0
            R.id.button16, R.id.button12, R.id.button8, R.id.button2, // + - * /
            R.id.button1, R.id.button3, R.id.button4,    // CE BS C
            R.id.button17, R.id.button19, R.id.button20  // +/- . =
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { handleButtonClick(it as Button) }
        }

        updateDisplay("0")
    }

    private fun handleButtonClick(button: Button) {
        val text = button.text.toString()

        when (text) {
            "C" -> {
                currentInput = ""
                operand1 = 0.0
                operator = ""
                updateDisplay("0")
            }
            "CE" -> {
                currentInput = ""
                updateDisplay("0")
            }
            "BS" -> {
                if (currentInput.isNotEmpty()) {
                    currentInput = currentInput.dropLast(1)
                    updateDisplay(if (currentInput.isEmpty()) "0" else currentInput)
                }
            }
            "+", "-", "X", "/" -> {
                if (currentInput.isNotEmpty()) {
                    operand1 = currentInput.toDouble()
                    operator = if (text == "X") "*" else text
                    isNewOp = true
                }
            }
            "=" -> {
                if (operator.isNotEmpty() && currentInput.isNotEmpty()) {
                    val operand2 = currentInput.toDouble()
                    val result = calculate(operand1, operand2, operator)
                    updateDisplay(result.toString())
                    currentInput = result.toString()
                    operator = ""
                }
            }
            "+/-" -> {
                if (currentInput.isNotEmpty()) {
                    currentInput = if (currentInput.startsWith("-")) {
                        currentInput.drop(1)
                    } else {
                        "-$currentInput"
                    }
                    updateDisplay(currentInput)
                }
            }
            "." -> {
                if (!currentInput.contains(".")) {
                    currentInput += "."
                    updateDisplay(currentInput)
                }
            }
            else -> { // Số
                if (isNewOp) {
                    currentInput = ""
                    isNewOp = false
                }
                currentInput += text
                updateDisplay(currentInput)
            }
        }
    }

    private fun calculate(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else Double.NaN
            else -> b
        }
    }

    private fun updateDisplay(value: String) {
        resultText.text = value
    }
}

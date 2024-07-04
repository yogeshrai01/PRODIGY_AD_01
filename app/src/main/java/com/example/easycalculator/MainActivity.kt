package com.example.easycalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
class Calculator {

    fun evaluateExpression(expression: String): Double {
        // Handle the arithmetic operations based on the expression
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            return result
        } catch (e: ArithmeticException) {
            // Handle division by zero or other e7rrors
            return Double.NaN
        } catch (e: Exception) {
            // Handle any other unexpected errors
            return Double.NaN
        }
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var expressionTextView: TextView
    private lateinit var resultTextView: TextView
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expressionTextView = findViewById(R.id.sol_tv)
        resultTextView = findViewById(R.id.res_tv)
    }

    fun onDigitClick(view: View) {
        val digit = (view as TextView).text.toString()
        expression += digit
        expressionTextView.text = expression
    }

    fun onopClick(view: View) {
        val operator = (view as TextView).text.toString()
        expression += operator
        expressionTextView.text = expression
    }

    fun onClearClick(view: View) {
        expression = ""
        expressionTextView.text = ""
        resultTextView.text = "0"
    }

    fun onEqualClick(view: View) {
        val calculator = Calculator()
        val result = calculator.evaluateExpression(expression)

        resultTextView.text = if (result.isNaN()) "Error" else result.toString()
    }

    fun onBackClick(view: View) {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            expressionTextView.text = expression
        }
    }
}

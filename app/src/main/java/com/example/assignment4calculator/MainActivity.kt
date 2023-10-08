package com.example.assignment4calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        val dish1EditText: EditText = findViewById(R.id.dish1EditText)
        val dish2EditText: EditText = findViewById(R.id.dish2EditText)
        val sweetEditText: EditText = findViewById(R.id.sweetEditText)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val tipPercentageSpinner: Spinner = findViewById(R.id.tipPercentageSpinner)
        val totalTextView: TextView = findViewById(R.id.totalTextView)
        val commentsEditText: EditText = findViewById(R.id.commentsEditText)

        // Set up the spinner with tip percentage options
        val tipPercentages = arrayOf(10, 15, 20, 25, 30)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipPercentages)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipPercentageSpinner.adapter = spinnerAdapter

        // Set a listener for the Calculate button
        calculateButton.setOnClickListener {
            // Parse the values from EditText fields
            val dish1Amount = parseEditTextValue(dish1EditText)
            val dish2Amount = parseEditTextValue(dish2EditText)
            val sweetAmount = parseEditTextValue(sweetEditText)

            // Calculate the subtotal
            val subtotal = dish1Amount + dish2Amount + sweetAmount

            // Get the selected tip percentage from the spinner
            val selectedTipPercentage = tipPercentages[tipPercentageSpinner.selectedItemPosition]

            // Calculate the tip amount
            val tipAmount = (subtotal * selectedTipPercentage / 100).toDouble()

            // Calculate the total
            val total = subtotal + tipAmount

            // Display the total with comments
            val comments = commentsEditText.text.toString()
            val totalWithComments = "Total Payment: $total\nComments: $comments"
            totalTextView.text = totalWithComments
        }
    }

    // Function to parse the value from an EditText field
    private fun parseEditTextValue(editText: EditText): Double {
        return editText.text.toString().toDoubleOrNull() ?: 0.0
    }
}

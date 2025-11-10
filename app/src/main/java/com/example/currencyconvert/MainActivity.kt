package com.example.currencyconvert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText

    // Tỷ giá cố định (so với USD)
    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "VND" to 25000.0,
        "EUR" to 0.9,
        "JPY" to 150.0,
        "GBP" to 0.8,
        "AUD" to 1.5,
        "CAD" to 1.35,
        "CNY" to 7.0,
        "KRW" to 1350.0,
        "THB" to 36.0
    )

    private val currencies = exchangeRates.keys.toList()
    private var isEditing = false // tránh vòng lặp cập nhật 2 EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        editTextFrom = findViewById(R.id.editTextFrom)
        editTextTo = findViewById(R.id.editTextTo)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        spinnerFrom.setSelection(0)
        spinnerTo.setSelection(1)

        // Khi nhập tiền → tự động tính
        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isEditing) convertCurrency(true)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Khi thay đổi loại tiền → tự động tính lại
        spinnerFrom.onItemSelectedListener = spinnerListener
        spinnerTo.onItemSelectedListener = spinnerListener
    }

    private val spinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
            convertCurrency(true)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun convertCurrency(fromToTarget: Boolean) {
        if (isEditing) return
        isEditing = true

        val fromCurrency = spinnerFrom.selectedItem.toString()
        val toCurrency = spinnerTo.selectedItem.toString()
        val amountStr = editTextFrom.text.toString()

        val amount = amountStr.toDoubleOrNull() ?: 0.0

        val result = if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            val usdAmount = amount / exchangeRates[fromCurrency]!!
            usdAmount * exchangeRates[toCurrency]!!
        } else 0.0

        editTextTo.setText(String.format("%.2f", result))
        isEditing = false
    }
}

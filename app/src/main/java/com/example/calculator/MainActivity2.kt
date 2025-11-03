package com.example.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etBirthday: EditText
    private lateinit var etAddress: EditText
    private lateinit var etEmail: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSelectBirthday: Button
    private lateinit var btnRegister: Button
    private lateinit var cvBirthday: CalendarView

    private var isCalendarVisible = false // Trạng thái hiển thị/ẩn CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai2)

        // Ánh xạ ID từ XML
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etBirthday = findViewById(R.id.etBirthday)
        etAddress = findViewById(R.id.etAddress)
        etEmail = findViewById(R.id.etEmail)
        rgGender = findViewById(R.id.rgGender)
        cbAgree = findViewById(R.id.cbAgree)
        btnSelectBirthday = findViewById(R.id.btnSelectBirthday)
        btnRegister = findViewById(R.id.btnRegister)
        cvBirthday = findViewById(R.id.cvBirthday)

        // Ẩn CalendarView lúc đầu
        cvBirthday.visibility = View.GONE

        // Nút Select: ẩn/hiện CalendarView
        btnSelectBirthday.setOnClickListener {
            isCalendarVisible = !isCalendarVisible
            cvBirthday.visibility = if (isCalendarVisible) View.VISIBLE else View.GONE
        }

        // Khi chọn ngày -> cập nhật EditText Birthday
        cvBirthday.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            etBirthday.setText(date)
            cvBirthday.visibility = View.GONE
            isCalendarVisible = false
        }

        // Nút Register -> kiểm tra dữ liệu
        btnRegister.setOnClickListener {
            validateInputs()
        }
    }

    private fun validateInputs() {
        var isValid = true

        // Danh sách các EditText cần kiểm tra
        val fields = listOf(etFirstName, etLastName, etBirthday, etAddress, etEmail)

        for (field in fields) {
            if (field.text.toString().trim().isEmpty()) {
                field.setBackgroundColor(Color.parseColor("#FFCDD2")) // Màu đỏ nhạt
                isValid = false
            } else {
                field.setBackgroundColor(Color.WHITE)
            }
        }

        // Kiểm tra giới tính
        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kiểm tra đồng ý điều khoản
        if (!cbAgree.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với điều khoản sử dụng", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        // Kết quả
        if (isValid) {
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.dssv

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), StudentAdapter.Listener {

    private lateinit var etMSSV: EditText
    private lateinit var etName: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var lvStudents: ListView

    private val students = ArrayList<Student>()
    private lateinit var adapter: StudentAdapter
    private var selectedIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        etMSSV = findViewById(R.id.etMSSV)
        etName = findViewById(R.id.etName)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        lvStudents = findViewById(R.id.lvStudents)

        adapter = StudentAdapter(this, students, this)
        lvStudents.adapter = adapter

        btnAdd.setOnClickListener { addStudent() }
        btnUpdate.setOnClickListener { updateStudent() }
    }

    private fun addStudent() {
        val mssv = etMSSV.text.toString().trim()
        val name = etName.text.toString().trim()

        if (mssv.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Nhập đầy đủ MSSV và Họ tên", Toast.LENGTH_SHORT).show()
            return
        }

        // validate MSSV
        if (mssv.length != 8) {
            Toast.makeText(this, "MSSV phải gồm 8 ký tự", Toast.LENGTH_SHORT).show()
            return
        }

        if (students.any { it.mssv == mssv }) {
            Toast.makeText(this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
            return
        }

        students.add(Student(name = name, mssv = mssv))
        adapter.notifyDataSetChanged()

        etMSSV.text.clear()
        etName.text.clear()
    }

    private fun updateStudent() {
        if (selectedIndex == -1) {
            Toast.makeText(this, "Chưa chọn sinh viên để cập nhật", Toast.LENGTH_SHORT).show()
            return
        }

        val newName = etName.text.toString().trim()

        if (newName.isEmpty()) {
            Toast.makeText(this, "Họ tên không được để trống", Toast.LENGTH_SHORT).show()
            return
        }

        students[selectedIndex].name = newName
        adapter.notifyDataSetChanged()

        // reset
        selectedIndex = -1
        etMSSV.isEnabled = true
        btnAdd.isEnabled = true
        etMSSV.text.clear()
        etName.text.clear()
    }

    override fun onDelete(position: Int) {
        students.removeAt(position)

        if (position == selectedIndex) {
            selectedIndex = -1
            etMSSV.isEnabled = true
            btnAdd.isEnabled = true
            etMSSV.text.clear()
            etName.text.clear()
        } else if (position < selectedIndex) {
            selectedIndex -= 1
        }

        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        val s = students[position]

        etMSSV.setText(s.mssv)
        etName.setText(s.name)
        etMSSV.isEnabled = false
        btnAdd.isEnabled = false

        etName.requestFocus()

        selectedIndex = position
    }
}

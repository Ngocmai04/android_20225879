package com.example.studentmanager.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.databinding.FragmentAddStudentBinding
import com.example.studentmanager.model.Student
import com.example.studentmanager.vm.StudentViewModel

class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!

    private val vm: StudentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddStudentBinding.bind(view)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnAdd.setOnClickListener {
            val mssv = binding.edtMssv.text?.toString()?.trim().orEmpty()
            val name = binding.edtName.text?.toString()?.trim().orEmpty()
            val phone = binding.edtPhone.text?.toString()?.trim().orEmpty()
            val address = binding.edtAddress.text?.toString()?.trim().orEmpty()

            if (mssv.isBlank() || name.isBlank()) {
                Toast.makeText(requireContext(), "MSSV và Họ tên là bắt buộc", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ok = vm.addStudent(Student(mssv, name, phone, address))
            if (!ok) {
                Toast.makeText(requireContext(), "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

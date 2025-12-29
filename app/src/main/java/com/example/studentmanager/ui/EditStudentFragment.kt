package com.example.studentmanager.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.databinding.FragmentEditStudentBinding
import com.example.studentmanager.model.Student
import com.example.studentmanager.vm.StudentViewModel
import com.example.studentmanager.vm.StudentViewModelFactory

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private var _binding: FragmentEditStudentBinding? = null
    private val binding get() = _binding!!

    private val vm: StudentViewModel by activityViewModels {
        StudentViewModelFactory(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditStudentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        val mssv = arguments?.getString("mssv")
        if (mssv == null) {
            Toast.makeText(requireContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }

        // Load student từ DB
        vm.loadStudent(mssv)

        // Observe student
        vm.selectedStudent.observe(viewLifecycleOwner) { student ->
            if (student == null) {
                Toast.makeText(requireContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
                return@observe
            }

            binding.edtMssv.setText(student.mssv)
            binding.edtName.setText(student.name)
            binding.edtPhone.setText(student.phone)
            binding.edtAddress.setText(student.address)
        }

        binding.btnUpdate.setOnClickListener {
            val newName = binding.edtName.text.toString().trim()
            val newPhone = binding.edtPhone.text.toString().trim()
            val newAddress = binding.edtAddress.text.toString().trim()

            if (newName.isBlank()) {
                Toast.makeText(requireContext(), "Họ tên là bắt buộc", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.updateStudent(
                Student(
                    mssv = mssv,
                    name = newName,
                    phone = newPhone,
                    address = newAddress
                )
            )

            findNavController().navigateUp()
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Xác nhận")
                .setMessage("Bạn có chắc muốn xóa sinh viên này không?")
                .setPositiveButton("Xóa") { _, _ ->
                    vm.deleteStudent(mssv)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

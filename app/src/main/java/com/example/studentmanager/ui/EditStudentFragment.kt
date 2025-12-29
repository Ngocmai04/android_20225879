package com.example.studentmanager.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentmanager.R
import com.example.studentmanager.databinding.FragmentEditStudentBinding
import com.example.studentmanager.model.Student
import com.example.studentmanager.vm.StudentViewModel

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private var _binding: FragmentEditStudentBinding? = null
    private val binding get() = _binding!!

    private val vm: StudentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEditStudentBinding.bind(view)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        val mssv = arguments?.getString("mssv")

        if (mssv == null) {
            Toast.makeText(requireContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }
        val student = vm.getStudentByMssv(mssv)

        if (student == null) {
            Toast.makeText(requireContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }

        // Fill UI
        binding.edtMssv.setText(student.mssv)
        binding.edtName.setText(student.name)
        binding.edtPhone.setText(student.phone)
        binding.edtAddress.setText(student.address)

        binding.btnUpdate.setOnClickListener {
            val newName = binding.edtName.text?.toString()?.trim().orEmpty()
            val newPhone = binding.edtPhone.text?.toString()?.trim().orEmpty()
            val newAddress = binding.edtAddress.text?.toString()?.trim().orEmpty()

            if (newName.isBlank()) {
                Toast.makeText(requireContext(), "Họ tên là bắt buộc", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ok = vm.updateStudent(
                Student(
                    mssv = mssv,
                    name = newName,
                    phone = newPhone,
                    address = newAddress
                )
            )

            if (!ok) {
                Toast.makeText(requireContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

package com.example.studentmanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.databinding.FragmentAddStudentBinding
import com.example.studentmanager.model.Student
import com.example.studentmanager.vm.StudentViewModel
import com.example.studentmanager.vm.StudentViewModelFactory

class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!

    private val vm: StudentViewModel by activityViewModels {
        StudentViewModelFactory(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddStudentBinding.bind(view)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnAdd.setOnClickListener {
            val student = Student(
                mssv = binding.edtMssv.text.toString(),
                name = binding.edtName.text.toString(),
                phone = binding.edtPhone.text.toString(),
                address = binding.edtAddress.text.toString()
            )

            vm.addStudent(student)
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

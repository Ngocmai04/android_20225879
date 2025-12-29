package com.example.studentmanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.databinding.FragmentStudentListBinding
import com.example.studentmanager.vm.StudentViewModel

class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    private val vm: StudentViewModel by activityViewModels()

    private lateinit var adapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStudentListBinding.bind(view)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = StudentAdapter(
            onClick = { student ->
                val bundle = Bundle().apply {
                    putString("mssv", student.mssv)
                }

                findNavController().navigate(
                    R.id.action_studentListFragment_to_editStudentFragment,
                    bundle
                )
            },
            onDelete = { student ->
                vm.deleteStudent(student.mssv)
            }
        )

        binding.rvStudents.adapter = adapter

        vm.students.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }


        binding.btnShowList.setOnClickListener {
            vm.students.value?.let {
                adapter.submitList(it)
            }
        }

        binding.btnAddStudent.setOnClickListener {
            findNavController().navigate(
                R.id.action_studentListFragment_to_addStudentFragment
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

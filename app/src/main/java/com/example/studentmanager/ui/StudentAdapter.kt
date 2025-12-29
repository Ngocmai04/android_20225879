package com.example.studentmanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.databinding.ItemStudentBinding
import com.example.studentmanager.model.Student

class StudentAdapter(
    private val onClick: (Student) -> Unit,
    private val onDelete: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentVH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.mssv == newItem.mssv
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentVH(binding)
    }

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentVH(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.tvMssv.text = "MSSV: ${student.mssv}"
            binding.tvName.text = "Họ tên: ${student.name}"
            binding.tvPhone.text = "SĐT: ${student.phone}"
            binding.tvAddress.text = "Địa chỉ: ${student.address}"

            binding.btnUpdate.setOnClickListener {
                onClick(student)
            }

            binding.root.setOnLongClickListener {
                onDelete(student)
                true
            }
        }
    }

}

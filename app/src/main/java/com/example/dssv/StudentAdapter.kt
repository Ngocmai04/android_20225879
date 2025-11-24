package com.example.dssv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

data class Student(
    var name: String,
    var mssv: String
)
class StudentAdapter(
    private val context: Context,
    private val data: ArrayList<Student>,
    private val listener: Listener
) : BaseAdapter() {

    interface Listener {
        fun onDelete(position: Int)
        fun onItemClick(position: Int)
    }

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): Any = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
            holder = ViewHolder(
                view.findViewById(R.id.tvName),
                view.findViewById(R.id.tvMSSV),
                view.findViewById(R.id.btnDelete),
                view.findViewById(R.id.llTexts)
            )
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val student = data[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv

        holder.btnDelete.setOnClickListener {
            listener.onDelete(position)
        }

        // Khi click vào item (text area)
        holder.llTexts.setOnClickListener {
            listener.onItemClick(position)
        }

        return view
    }

    private data class ViewHolder(
        val tvName: TextView,
        val tvMSSV: TextView,
        val btnDelete: ImageButton,
        val llTexts: LinearLayout
    )
}

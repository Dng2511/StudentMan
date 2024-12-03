package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class StudentFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_form, container, false)
        val editName = view.findViewById<EditText>(R.id.edit_student_name)
        val editId = view.findViewById<EditText>(R.id.edit_student_id)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        val student = arguments?.getParcelable<StudentModel>("student")
        if (student != null) {
            editName.setText(student.name)
            editId.setText(student.id)
        }

        btnSave.setOnClickListener {
            val name = editName.text.toString()
            val id = editId.text.toString()

            if (name.isNotBlank() && id.isNotBlank()) {
                val updatedStudent = StudentModel(name, id)
                val action = StudentFormFragmentDirections
                    .actionToStudentListFragment(updatedStudent)
                findNavController().navigate(action)
            }
        }

        return view
    }
}

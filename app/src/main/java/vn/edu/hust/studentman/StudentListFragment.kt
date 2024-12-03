package vn.edu.hust.studentman

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {

    private lateinit var studentAdapter: ArrayAdapter<StudentModel>
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        val listView: ListView = view.findViewById(R.id.list_view_students)

        // Thiết lập Adapter
        studentAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, students
        )
        listView.adapter = studentAdapter

        // Context Menu
        registerForContextMenu(listView)

        return view
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add("Edit").setOnMenuItemClickListener {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            val student = students[info.position]
            val action = StudentListFragmentDirections
                .actionToStudentFormFragment(student)
            findNavController().navigate(action)
            true
        }
        menu.add("Remove").setOnMenuItemClickListener {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            students.removeAt(info.position)
            studentAdapter.notifyDataSetChanged()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_new -> {
                val action = StudentListFragmentDirections
                    .actionToStudentFormFragment(null)
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


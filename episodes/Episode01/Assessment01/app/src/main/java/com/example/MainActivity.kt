package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView

class MainActivity : AppCompatActivity() {

    private lateinit var manager: StudentManager
    private lateinit var contentLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = StudentManager()

        // Find views
        contentLayout = findViewById(R.id.content_layout)
        val studentNameInput = findViewById<EditText>(R.id.et_student_name)
        val studentIdInput = findViewById<EditText>(R.id.et_student_id)
        val gradeInput = findViewById<EditText>(R.id.et_grade)
        val addStudentBtn = findViewById<Button>(R.id.btn_add_student)
        val addGradeBtn = findViewById<Button>(R.id.btn_add_grade)

        // Add student button
        addStudentBtn.setOnClickListener {
            try {
                val name = studentNameInput.text.toString()
                val id = studentIdInput.text.toString()
                val student = Student(id, name, "student@example.com")
                manager.addStudent(student)
                studentNameInput.text.clear()
                studentIdInput.text.clear()
                updateDisplay()
            } catch (e: Exception) {
                contentLayout.findViewWithTag<TextView>("error")?.text = "Error: ${e.message}"
            }
        }

        // Add grade button
        addGradeBtn.setOnClickListener {
            try {
                val gradeValue = gradeInput.text.toString().toDouble()
                val studentId = studentIdInput.text.toString()
                manager.getStudent(studentId)?.addGrade(gradeValue)
                gradeInput.text.clear()
                updateDisplay()
            } catch (e: Exception) {
                contentLayout.findViewWithTag<TextView>("error")?.text = "Error: ${e.message}"
            }
        }

        updateDisplay()
    }

    private fun updateDisplay() {
        val students = manager.getAllStudents()
        val classAvg = manager.getClassAverage()
        val passPercent = manager.getPassPercentage()

        val textView = findViewById<TextView>(R.id.tv_title)
        textView.text = buildString {
            append("Student Grade Manager\n")
            append("Students: ${manager.getStudentCount()}\n")
            append("Class Average: ${String.format("%.2f", classAvg)}\n")
            append("Pass Rate: ${String.format("%.1f", passPercent)}%")
        }
    }

    fun getManager(): StudentManager = manager
}

package com.example

/**
 * Manager class for handling student operations and calculations
 */
class StudentManager {
    private val students = mutableMapOf<String, Student>()

    fun addStudent(student: Student) {
        if (student.id.isBlank()) {
            throw IllegalArgumentException("Student ID cannot be empty")
        }
        if (student.name.isBlank()) {
            throw IllegalArgumentException("Student name cannot be empty")
        }
        if (!student.email.contains("@")) {
            throw IllegalArgumentException("Invalid email format")
        }
        students[student.id] = student
    }

    fun getStudent(id: String): Student? = students[id]

    fun removeStudent(id: String): Boolean = students.remove(id) != null

    fun getAllStudents(): List<Student> = students.values.toList()

    fun getStudentCount(): Int = students.size

    fun getTopStudent(): Student? {
        return students.values.maxByOrNull { it.getAverageGrade() }
    }

    fun getStudentsWithAverageAbove(threshold: Double): List<Student> {
        return students.values.filter { it.getAverageGrade() >= threshold }
    }

    fun getPassPercentage(): Double {
        if (students.isEmpty()) return 0.0
        val passCount = students.values.count { it.getPassStatus() }
        return (passCount.toDouble() / students.size) * 100
    }

    fun getClassAverage(): Double {
        if (students.isEmpty()) return 0.0
        return students.values.map { it.getAverageGrade() }.average()
    }

    fun clearAllStudents() {
        students.clear()
    }
}

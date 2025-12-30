package com.example

/**
 * Data class representing a Student with grades
 */
data class Student(
    val id: String,
    val name: String,
    val email: String,
    val grades: MutableList<Double> = mutableListOf()
) {
    fun addGrade(grade: Double) {
        if (grade in 0.0..100.0) {
            grades.add(grade)
        } else {
            throw IllegalArgumentException("Grade must be between 0 and 100")
        }
    }

    fun removeGrade(index: Int) {
        if (index in grades.indices) {
            grades.removeAt(index)
        }
    }

    fun getAverageGrade(): Double {
        return if (grades.isEmpty()) 0.0 else grades.average()
    }

    fun getTotalGrades(): Int = grades.size

    fun getPassStatus(): Boolean {
        return getAverageGrade() >= 60.0
    }

    fun getLetterGrade(): String {
        return when (val avg = getAverageGrade()) {
            in 90.0..100.0 -> "A"
            in 80.0..89.9 -> "B"
            in 70.0..79.9 -> "C"
            in 60.0..69.9 -> "D"
            else -> "F"
        }
    }
}

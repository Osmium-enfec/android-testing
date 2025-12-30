package com.example

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class StudentTest {

    private lateinit var student: Student
    private val reporter = TestReportGenerator.getInstance()

    @Before
    fun setup() {
        student = Student("STU001", "John Doe", "john@example.com")
    }

    @Test
    fun testStudentCreation() {
        try {
            assertEquals("STU001", student.id)
            assertEquals("John Doe", student.name)
            assertEquals("john@example.com", student.email)
            assertTrue(student.grades.isEmpty())
            reporter.addTestResult("testStudentCreation", true)
        } catch (e: Exception) {
            reporter.addTestResult("testStudentCreation", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAddGradeValid() {
        try {
            student.addGrade(85.0)
            assertEquals(1, student.getTotalGrades())
            assertTrue(student.grades.contains(85.0))
            reporter.addTestResult("testAddGradeValid", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddGradeValid", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAddGradeInvalid() {
        try {
            assertThrows(IllegalArgumentException::class.java) {
                student.addGrade(150.0)
            }
            assertThrows(IllegalArgumentException::class.java) {
                student.addGrade(-10.0)
            }
            reporter.addTestResult("testAddGradeInvalid", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddGradeInvalid", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAddMultipleGrades() {
        try {
            student.addGrade(80.0)
            student.addGrade(90.0)
            student.addGrade(75.0)
            assertEquals(3, student.getTotalGrades())
            reporter.addTestResult("testAddMultipleGrades", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddMultipleGrades", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAverageGradeCalculation() {
        try {
            student.addGrade(80.0)
            student.addGrade(90.0)
            student.addGrade(100.0)
            val average = student.getAverageGrade()
            assertEquals(90.0, average, 0.01)
            reporter.addTestResult("testAverageGradeCalculation", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAverageGradeCalculation", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAverageGradeEmpty() {
        try {
            val average = student.getAverageGrade()
            assertEquals(0.0, average, 0.01)
            reporter.addTestResult("testAverageGradeEmpty", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAverageGradeEmpty", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testRemoveGrade() {
        try {
            student.addGrade(80.0)
            student.addGrade(90.0)
            student.addGrade(75.0)
            student.removeGrade(1)
            assertEquals(2, student.getTotalGrades())
            assertFalse(student.grades.contains(90.0))
            reporter.addTestResult("testRemoveGrade", true)
        } catch (e: Exception) {
            reporter.addTestResult("testRemoveGrade", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetLetterGradeA() {
        try {
            student.addGrade(95.0)
            assertEquals("A", student.getLetterGrade())
            reporter.addTestResult("testGetLetterGradeA", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetLetterGradeA", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetLetterGradeB() {
        try {
            student.addGrade(85.0)
            assertEquals("B", student.getLetterGrade())
            reporter.addTestResult("testGetLetterGradeB", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetLetterGradeB", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetLetterGradeF() {
        try {
            student.addGrade(45.0)
            assertEquals("F", student.getLetterGrade())
            reporter.addTestResult("testGetLetterGradeF", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetLetterGradeF", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testPassStatus() {
        try {
            student.addGrade(70.0)
            assertTrue(student.getPassStatus())
            
            val failStudent = Student("STU002", "Jane Doe", "jane@example.com")
            failStudent.addGrade(50.0)
            assertFalse(failStudent.getPassStatus())
            
            reporter.addTestResult("testPassStatus", true)
        } catch (e: Exception) {
            reporter.addTestResult("testPassStatus", false, e.message ?: "Test failed")
            throw e
        }
    }
}

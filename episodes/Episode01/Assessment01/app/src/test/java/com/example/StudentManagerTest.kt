package com.example

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class StudentManagerTest {

    private lateinit var manager: StudentManager
    private val reporter = TestReportGenerator.getInstance()

    @Before
    fun setup() {
        manager = StudentManager()
    }

    @Test
    fun testAddStudent() {
        try {
            val student = Student("STU001", "John", "john@example.com")
            manager.addStudent(student)
            assertEquals(1, manager.getStudentCount())
            reporter.addTestResult("testAddStudent", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddStudent", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAddStudentWithEmptyId() {
        try {
            assertThrows(IllegalArgumentException::class.java) {
                manager.addStudent(Student("", "John", "john@example.com"))
            }
            reporter.addTestResult("testAddStudentWithEmptyId", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddStudentWithEmptyId", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testAddStudentWithInvalidEmail() {
        try {
            assertThrows(IllegalArgumentException::class.java) {
                manager.addStudent(Student("STU001", "John", "invalid-email"))
            }
            reporter.addTestResult("testAddStudentWithInvalidEmail", true)
        } catch (e: Exception) {
            reporter.addTestResult("testAddStudentWithInvalidEmail", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetStudent() {
        try {
            val student = Student("STU001", "John", "john@example.com")
            manager.addStudent(student)
            val retrieved = manager.getStudent("STU001")
            assertNotNull(retrieved)
            assertEquals("John", retrieved?.name)
            reporter.addTestResult("testGetStudent", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetStudent", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testRemoveStudent() {
        try {
            val student = Student("STU001", "John", "john@example.com")
            manager.addStudent(student)
            assertTrue(manager.removeStudent("STU001"))
            assertEquals(0, manager.getStudentCount())
            assertNull(manager.getStudent("STU001"))
            reporter.addTestResult("testRemoveStudent", true)
        } catch (e: Exception) {
            reporter.addTestResult("testRemoveStudent", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetAllStudents() {
        try {
            manager.addStudent(Student("STU001", "John", "john@example.com"))
            manager.addStudent(Student("STU002", "Jane", "jane@example.com"))
            manager.addStudent(Student("STU003", "Bob", "bob@example.com"))
            val students = manager.getAllStudents()
            assertEquals(3, students.size)
            reporter.addTestResult("testGetAllStudents", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetAllStudents", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetClassAverage() {
        try {
            val s1 = Student("STU001", "John", "john@example.com")
            s1.addGrade(90.0)
            manager.addStudent(s1)

            val s2 = Student("STU002", "Jane", "jane@example.com")
            s2.addGrade(80.0)
            manager.addStudent(s2)

            val classAvg = manager.getClassAverage()
            assertEquals(85.0, classAvg, 0.01)
            reporter.addTestResult("testGetClassAverage", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetClassAverage", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetPassPercentage() {
        try {
            val s1 = Student("STU001", "John", "john@example.com")
            s1.addGrade(70.0)
            manager.addStudent(s1)

            val s2 = Student("STU002", "Jane", "jane@example.com")
            s2.addGrade(50.0)
            manager.addStudent(s2)

            val passPercent = manager.getPassPercentage()
            assertEquals(50.0, passPercent, 0.01)
            reporter.addTestResult("testGetPassPercentage", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetPassPercentage", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetTopStudent() {
        try {
            val s1 = Student("STU001", "John", "john@example.com")
            s1.addGrade(80.0)
            manager.addStudent(s1)

            val s2 = Student("STU002", "Jane", "jane@example.com")
            s2.addGrade(95.0)
            manager.addStudent(s2)

            val topStudent = manager.getTopStudent()
            assertNotNull(topStudent)
            assertEquals("Jane", topStudent?.name)
            reporter.addTestResult("testGetTopStudent", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetTopStudent", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testGetStudentsAboveThreshold() {
        try {
            val s1 = Student("STU001", "John", "john@example.com")
            s1.addGrade(85.0)
            manager.addStudent(s1)

            val s2 = Student("STU002", "Jane", "jane@example.com")
            s2.addGrade(75.0)
            manager.addStudent(s2)

            val aboveThreshold = manager.getStudentsWithAverageAbove(80.0)
            assertEquals(1, aboveThreshold.size)
            assertEquals("John", aboveThreshold[0].name)
            reporter.addTestResult("testGetStudentsAboveThreshold", true)
        } catch (e: Exception) {
            reporter.addTestResult("testGetStudentsAboveThreshold", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testClearAllStudents() {
        try {
            manager.addStudent(Student("STU001", "John", "john@example.com"))
            manager.addStudent(Student("STU002", "Jane", "jane@example.com"))
            assertEquals(2, manager.getStudentCount())
            
            manager.clearAllStudents()
            assertEquals(0, manager.getStudentCount())
            reporter.addTestResult("testClearAllStudents", true)
        } catch (e: Exception) {
            reporter.addTestResult("testClearAllStudents", false, e.message ?: "Test failed")
            throw e
        }
    }

    @Test
    fun testEmptyManagerStatistics() {
        try {
            assertEquals(0.0, manager.getClassAverage(), 0.01)
            assertEquals(0.0, manager.getPassPercentage(), 0.01)
            assertNull(manager.getTopStudent())
            assertEquals(0, manager.getStudentCount())
            reporter.addTestResult("testEmptyManagerStatistics", true)
        } catch (e: Exception) {
            reporter.addTestResult("testEmptyManagerStatistics", false, e.message ?: "Test failed")
            throw e
        }
    }
}

package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.AfterClass
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private val reporter = TestReportGenerator.getInstance()

    @Test
    fun testActivityLaunches() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .resume()
                .get()

            assertNotNull("Activity should not be null", activity)
            assertTrue("Activity should be visible", activity.isVisible)
            reporter.addTestResult("testActivityLaunches", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testActivityLaunches", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testActivityLaunches", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testActivityHasInputFields() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val studentNameInput = activity.findViewById<android.widget.EditText>(R.id.et_student_name)
            val studentIdInput = activity.findViewById<android.widget.EditText>(R.id.et_student_id)
            val gradeInput = activity.findViewById<android.widget.EditText>(R.id.et_grade)

            assertNotNull("Student name input should exist", studentNameInput)
            assertNotNull("Student ID input should exist", studentIdInput)
            assertNotNull("Grade input should exist", gradeInput)
            reporter.addTestResult("testActivityHasInputFields", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testActivityHasInputFields", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testActivityHasInputFields", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testActivityHasButtons() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val addStudentBtn = activity.findViewById<android.widget.Button>(R.id.btn_add_student)
            val addGradeBtn = activity.findViewById<android.widget.Button>(R.id.btn_add_grade)

            assertNotNull("Add Student button should exist", addStudentBtn)
            assertNotNull("Add Grade button should exist", addGradeBtn)
            assertTrue("Add Student button should be clickable", addStudentBtn?.isClickable == true)
            assertTrue("Add Grade button should be clickable", addGradeBtn?.isClickable == true)
            reporter.addTestResult("testActivityHasButtons", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testActivityHasButtons", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testActivityHasButtons", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testManagerInitialization() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val manager = activity.getManager()
            assertNotNull("Manager should be initialized", manager)
            assertEquals("Manager should have no students initially", 0, manager.getStudentCount())
            reporter.addTestResult("testManagerInitialization", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testManagerInitialization", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testManagerInitialization", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testTitleDisplaysCorrectText() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val titleView = activity.findViewById<android.widget.TextView>(R.id.tv_title)
            assertNotNull("Title view should exist", titleView)
            assertTrue("Title should contain 'Student Grade Manager'", 
                titleView?.text.toString().contains("Student Grade Manager"))
            reporter.addTestResult("testTitleDisplaysCorrectText", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testTitleDisplaysCorrectText", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testTitleDisplaysCorrectText", false, "Exception: ${e.message}")
            throw e
        }
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun generateTestReport() {
            val reporter = TestReportGenerator.getInstance()
            reporter.generateReport(
                assignmentName = "EPISODE 01 - ASSESSMENT 01: STUDENT GRADE MANAGER",
                outputPath = "test_report.txt"
            )
            reporter.printQuickSummary()
        }
    }
}

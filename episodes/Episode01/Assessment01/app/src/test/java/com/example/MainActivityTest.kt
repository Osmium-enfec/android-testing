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
    fun testActivityHasViews() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val titleView = activity.findViewById<android.widget.TextView>(R.id.tv_title)
            val messageView = activity.findViewById<android.widget.TextView>(R.id.tv_message)
            val submitButton = activity.findViewById<android.widget.Button>(R.id.btn_submit)

            assertNotNull("Title view should exist", titleView)
            assertNotNull("Message view should exist", messageView)
            assertNotNull("Submit button should exist", submitButton)
            reporter.addTestResult("testActivityHasViews", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testActivityHasViews", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testActivityHasViews", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testButtonClickable() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val submitButton = activity.findViewById<android.widget.Button>(R.id.btn_submit)
            assertNotNull("Submit button should exist", submitButton)
            assertTrue("Submit button should be clickable", submitButton?.isClickable == true)
            reporter.addTestResult("testButtonClickable", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testButtonClickable", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testButtonClickable", false, "Exception: ${e.message}")
            throw e
        }
    }

    @Test
    fun testTextViewContent() {
        try {
            val activity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .get()

            val titleView = activity.findViewById<android.widget.TextView>(R.id.tv_title)
            assertEquals("Title should match", "Welcome to Episode 01", titleView?.text.toString())
            reporter.addTestResult("testTextViewContent", true)
        } catch (e: AssertionError) {
            reporter.addTestResult("testTextViewContent", false, e.message ?: "Assertion failed")
            throw e
        } catch (e: Exception) {
            reporter.addTestResult("testTextViewContent", false, "Exception: ${e.message}")
            throw e
        }
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun generateTestReport() {
            val reporter = TestReportGenerator.getInstance()
            reporter.generateReport(
                assignmentName = "EPISODE 01 - ASSESSMENT 01: MAIN ACTIVITY",
                outputPath = "test_report.txt"
            )
            reporter.printQuickSummary()
        }
    }
}

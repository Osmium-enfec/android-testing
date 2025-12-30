package com.example

import org.junit.runner.Result
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Generates a detailed test report similar to pytest output
 * Shows passed/failed tests, errors, and final score
 */
class TestReportGenerator {

    data class TestResult(
        val testName: String,
        val passed: Boolean,
        val errorMessage: String? = null
    )

    private val testResults = mutableListOf<TestResult>()
    private val startTime = System.currentTimeMillis()

    /**
     * Add a test result to the report
     */
    fun addTestResult(testName: String, passed: Boolean, errorMessage: String? = null) {
        testResults.add(TestResult(testName, passed, errorMessage))
        println(if (passed) "✓ $testName PASSED" else "✗ $testName FAILED: $errorMessage")
    }

    /**
     * Generate and save the test report
     */
    fun generateReport(
        assignmentName: String = "ANDROID ASSIGNMENT TEST",
        outputPath: String = "test_report.txt"
    ) {
        val endTime = System.currentTimeMillis()
        val duration = (endTime - startTime) / 1000.0

        val passed = testResults.count { it.passed }
        val failed = testResults.count { !it.passed }
        val total = testResults.size
        val score = if (total > 0) (passed.toDouble() / total) * 10 else 0.0
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

        val report = buildString {
            append("${separator(70)}\n")
            append("STUDENT ASSIGNMENT TEST REPORT\n")
            append(assignmentName.padEnd(35) + " | $timestamp\n")
            append("${separator(70)}\n\n")

            // Test Details
            append("TEST RESULTS:\n")
            append(separator(70) + "\n")
            testResults.forEachIndexed { index, result ->
                val status = if (result.passed) "✓ PASSED" else "✗ FAILED"
                append("${index + 1}. ${result.testName}: $status\n")
                if (result.errorMessage != null) {
                    append("   Error: ${result.errorMessage}\n")
                }
            }

            // Summary
            append("\n" + separator(70) + "\n")
            append("SUMMARY\n")
            append(separator(70) + "\n")
            append("Tests Passed: $passed/$total\n")
            append("Tests Failed: $failed/$total\n")
            append("Execution Time: ${String.format("%.2f", duration)}s\n")

            // Errors Section
            if (failed > 0) {
                append("\nERRORS FOUND:\n")
                testResults.filter { !it.passed }.forEachIndexed { index, result ->
                    append("${index + 1}. ${result.testName}\n")
                    append("   ${result.errorMessage}\n")
                }
            } else {
                append("\n✓ NO ERRORS FOUND - Code is correct!\n")
            }

            // Final Score and Feedback
            append("\n" + separator(70) + "\n")
            append("FINAL SCORE: ${String.format("%.1f", score)}/10\n")
            append(separator(70) + "\n")

            when {
                score == 10.0 -> append("★ EXCELLENT WORK! Perfect implementation! ★\n")
                score >= 7.0 -> append("★ GOOD WORK! Your code is mostly correct. ★\n")
                score >= 5.0 -> append("★ FAIR - Some issues to fix. ★\n")
                else -> append("★ NEEDS IMPROVEMENT - Please review the errors above. ★\n")
            }

            append("${separator(70)}\n")
        }

        // Print to console
        println("\n$report")

        // Save to file
        try {
            File(outputPath).writeText(report)
            println("Report saved to: $outputPath")
        } catch (e: Exception) {
            println("Error saving report: ${e.message}")
        }
    }

    private fun separator(length: Int): String = "=".repeat(length)

    /**
     * Generate a quick summary (used during test execution)
     */
    fun printQuickSummary() {
        val passed = testResults.count { it.passed }
        val failed = testResults.count { !it.passed }
        val total = testResults.size
        val score = if (total > 0) (passed.toDouble() / total) * 10 else 0.0

        println("\n" + "=".repeat(70))
        println("QUICK SUMMARY: $passed/$total tests passed | Score: ${String.format("%.1f", score)}/10")
        println("=".repeat(70) + "\n")
    }

    companion object {
        @Volatile
        private var instance: TestReportGenerator? = null

        fun getInstance(): TestReportGenerator {
            return instance ?: synchronized(this) {
                instance ?: TestReportGenerator().also { instance = it }
            }
        }
    }
}

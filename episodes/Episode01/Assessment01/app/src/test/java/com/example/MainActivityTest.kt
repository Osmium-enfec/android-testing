package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun testActivityLaunches() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        assertNotNull("Activity should not be null", activity)
        assertTrue("Activity should be visible", activity.isVisible)
    }

    @Test
    fun testActivityHasViews() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .get()

        val titleView = activity.findViewById<android.widget.TextView>(R.id.tv_title)
        val messageView = activity.findViewById<android.widget.TextView>(R.id.tv_message)
        val submitButton = activity.findViewById<android.widget.Button>(R.id.btn_submit)

        assertNotNull("Title view should exist", titleView)
        assertNotNull("Message view should exist", messageView)
        assertNotNull("Submit button should exist", submitButton)
    }

    @Test
    fun testButtonClickable() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .get()

        val submitButton = activity.findViewById<android.widget.Button>(R.id.btn_submit)
        assertNotNull("Submit button should exist", submitButton)
        assertTrue("Submit button should be clickable", submitButton?.isClickable == true)
    }

    @Test
    fun testTextViewContent() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .get()

        val titleView = activity.findViewById<android.widget.TextView>(R.id.tv_title)
        assertEquals("Title should match", "Welcome to Episode 01", titleView?.text.toString())
    }
}

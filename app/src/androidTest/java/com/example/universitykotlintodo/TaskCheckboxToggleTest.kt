package com.example.universitykotlintodo

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*

@RunWith(AndroidJUnit4::class)
class TaskCheckboxToggleTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkboxTogglesTaskStatus() {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("task_checkbox_1").fetchSemanticsNodes().isNotEmpty()
        }

        val checkbox = composeTestRule.onNodeWithTag("task_checkbox_1")

        checkbox.assertIsOff()

        checkbox.performClick()

        checkbox.assertIsOn()
    }
}

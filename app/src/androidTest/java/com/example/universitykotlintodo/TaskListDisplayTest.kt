package com.example.universitykotlintodo

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*

@RunWith(AndroidJUnit4::class)
class TaskListDisplayTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun displaysAllThreeTasksFromJson() {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("task_card_1").fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithTag("task_card_2").fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithTag("task_card_3").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("task_card_1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("task_card_2").assertIsDisplayed()
        composeTestRule.onNodeWithTag("task_card_3").assertIsDisplayed()

        composeTestRule.onNodeWithTag("task_card_1").assertExists()
        composeTestRule.onNodeWithTag("task_card_2").assertExists()
        composeTestRule.onNodeWithTag("task_card_3").assertExists()
    }
}

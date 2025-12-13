package com.example.universitykotlintodo

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*

@RunWith(AndroidJUnit4::class)
class TaskNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigateListToDetailAndBack() {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("task_card_2").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("task_card_2").performClick()

        composeTestRule.waitUntil(5_000) {
            composeTestRule.onAllNodesWithTag("task_title_2").fetchSemanticsNodes().isNotEmpty() ||
            composeTestRule.onAllNodesWithContentDescription("Назад").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("task_title_2").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Назад").performClick()

        composeTestRule.waitUntil(5_000) {
            composeTestRule.onAllNodesWithTag("task_card_2").fetchSemanticsNodes().isNotEmpty()
        }
    }
}

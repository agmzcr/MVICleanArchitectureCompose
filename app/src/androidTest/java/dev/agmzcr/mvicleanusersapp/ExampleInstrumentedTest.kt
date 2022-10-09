package dev.agmzcr.mvicleanusersapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.agmzcr.mvicleanusersapp.presentation.MainActivity
import dev.agmzcr.mvicleanusersapp.presentation.ScreenSetup
import dev.agmzcr.mvicleanusersapp.presentation.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addingItemsWorksCorrectly() {
        composeTestRule.activity.setContent {
            ScreenSetup()
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Name 1 LastName 1").assertExists()
    }

    @Test
    fun removingItemsWorkCorrectly() {
            composeTestRule.activity.setContent {
                ScreenSetup()
            }
            composeTestRule.onNodeWithContentDescription("Add").performClick()
            composeTestRule.onNodeWithText("Name 0 LastName 0").assertExists()

            composeTestRule.onNodeWithContentDescription("Remove").performClick()
            composeTestRule.onNodeWithText("Name 0 LastName 0").assertDoesNotExist()
    }

    @Test
    fun loadingIsVisibleWhenAddingNewItem() {
        composeTestRule.activity.setContent {
            HomeScreen(users = emptyList(), isLoading = true)
        }
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithTag("loadingCard").assertExists()
    }
}

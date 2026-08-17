package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.ui.MyCityApp
import com.example.mycityapp.ui.MyCityAppScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyCityAppNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupMyCityNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MyCityApp(
                navController = navController
            )
        }

    }

    @Test
    fun myCityAppNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(MyCityAppScreen.Start.name)
    }

    @Test
    fun myCityAppNavHost_verifyBackNavigationNotShowOnStartScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun myCityAppNavHost_verifyNavigationToRecommendations() {
        // click on default category name and test if it redirects to recommendations screen
        composeTestRule
            .onNodeWithText(LocalDataProvider.defaultCategory.name)
            .performClick()
        navController.assertCurrentRouteName(MyCityAppScreen.Recommendations.name)
    }

    @Test
    fun myCityAppNavHost_verifyBackNavigationFromRecommendations() {
       //Arrange
        composeTestRule
            .onNodeWithText(LocalDataProvider.defaultCategory.name)
            .performClick()

        //Action - click back button
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.back_button)
        ).performClick()

        //Assert - check if user is back on Start Screen
        navController.assertCurrentRouteName(MyCityAppScreen.Start.name)

    }


}
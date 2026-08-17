package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.ui.MyCityApp
import org.junit.Rule
import org.junit.Test

class MyCityAppResizableUiAdaptionTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestExpandedWidth
    fun expandedDevice_showSelectedPlaceAndDetailsTogether() {
        //Arrange
        val stateRestorationTest = StateRestorationTester(composeTestRule)
        stateRestorationTest.setContent {
            MyCityApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        val defaultCategory = LocalDataProvider.defaultCategory
        val selectedPlace = LocalDataProvider.getPlacesByCategory(
            defaultCategory.id
        )[1]

        //Action
        val listTag = composeTestRule.activity.getString(R.string.tag_recommendation_list)
        composeTestRule.onNodeWithText(defaultCategory.name).performClick()
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag(listTag).fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText(selectedPlace.name).performClick()

        //Assert
        val detailsTag = composeTestRule.activity.getString(R.string.tag_recommendation_details)
        composeTestRule
            .onNodeWithTag(detailsTag)
            .onChildren()
            .assertAny(
                hasText(
                    selectedPlace.description
                )
            )
    }
}
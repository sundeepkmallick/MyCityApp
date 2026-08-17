package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(expectedRouteName: String){
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""
    assertEquals(true, currentRoute.startsWith(expectedRouteName))
}

fun<A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(@StringRes id: Int)
        : SemanticsNodeInteraction = onNodeWithText(activity.getString(id))
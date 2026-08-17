package com.example.mycityapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycityapp.R
import com.example.mycityapp.ui.theme.MyCityAppTheme
import com.example.mycityapp.ui.utils.MyCityAppContentType


enum class MyCityAppScreen(val title: Int) {
    Start(R.string.my_city_berlin),
    Recommendations(R.string.recommendations),
    RecommendedPlace(R.string.recommended_place)
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {

    val contentType: MyCityAppContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            MyCityAppContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            MyCityAppContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            MyCityAppContentType.LIST_AND_DETAIL
        }

        else -> {
            MyCityAppContentType.LIST_ONLY
        }
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: MyCityAppScreen.Start.name
    val currentScreen = try {
        MyCityAppScreen.valueOf(currentRoute.substringBefore('/'))
    } catch (e: IllegalArgumentException) {
        MyCityAppScreen.Start
    }

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyCityAppScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityAppScreen.Start.name) {
                Categories(
                    contentType = contentType,
                    onCategoryListItemClick = { categoryId ->
                        navController.navigate(
                            route = "${MyCityAppScreen.Recommendations.name}/$categoryId"
                        )
                    }
                )
            }

            composable(
                route = "${MyCityAppScreen.Recommendations.name}/{categoryId}",
                arguments = listOf(
                    navArgument(
                        "categoryId",
                        builder = {
                            type = NavType.StringType
                        }
                    )
                )
            ) {
                val categoryId = backStackEntry?.arguments?.getString("categoryId") ?: ""
                Recommendations(
                    contentType = contentType,
                    categoryId = categoryId,
                    onPlaceClick = { placeId ->
                        if (contentType == MyCityAppContentType.LIST_ONLY) {
                            navController.navigate("${MyCityAppScreen.RecommendedPlace.name}/$placeId")
                        }
                    }
                )
            }

            composable(
                route = "${MyCityAppScreen.RecommendedPlace.name}/{placeId}",
                arguments = listOf(
                    navArgument("placeId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val placeId = backStackEntry.arguments?.getString("placeId") ?: ""
                RecommendationDetailsScreen(placeId)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        modifier = Modifier,
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
            subtitleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Preview
@Composable
private fun MyCityAppPreview() {
    MyCityAppTheme {
        Surface {
            MyCityApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
    }
}
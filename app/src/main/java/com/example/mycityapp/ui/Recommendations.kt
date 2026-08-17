package com.example.mycityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.mycityapp.R
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.model.Place
import com.example.mycityapp.ui.theme.MyCityAppTheme
import com.example.mycityapp.ui.utils.MyCityAppContentType

@Composable
fun Recommendations(
    modifier: Modifier = Modifier,
    contentType: MyCityAppContentType,
    categoryId: String,
    onPlaceClick: (String) -> Unit
) {
    val viewModel: RecommendationsViewModel = viewModel()
    LaunchedEffect(categoryId) {
        viewModel.loadRecommendations(categoryId)
    }

    val uiState: RecommendationsUiState by viewModel.uiState.collectAsStateWithLifecycle()


    fun onRecommendationListItemClick(placeId: String) {
        viewModel.updateSelectedPlace(
            placeId = placeId
        )
        if(contentType == MyCityAppContentType.LIST_ONLY){
            onPlaceClick(placeId)
        }
    }

    when (contentType) {
        MyCityAppContentType.LIST_ONLY -> {
            RecommendationList(modifier, uiState.recommendations, contentType) {
                onRecommendationListItemClick(it)
            }
        }

        MyCityAppContentType.LIST_AND_DETAIL -> {
            RecommendationListAndDetails(modifier, uiState, contentType) {
                onRecommendationListItemClick(it)
            }
        }
    }
}

@Composable
fun RecommendationListAndDetails(
    modifier: Modifier,
    uiState: RecommendationsUiState,
    contentType: MyCityAppContentType,
    onRecommendationListItemClick: (String) -> Unit
) {

    Row(
        modifier = modifier
    ) {
        RecommendationList(
            modifier.weight(0.4f),
            uiState.recommendations,
            contentType,
            onRecommendationListItemClick
        )

        RecommendationDetails(
            modifier
                .weight(0.6f)
                .testTag(stringResource(R.string.tag_recommendation_details)),
            uiState.selectedPlace)
    }
}

@Composable
fun RecommendationDetailsScreen(
    placeId: String,
    viewModel: RecommendationsViewModel = viewModel()
) {
    LaunchedEffect(placeId) {
        viewModel.loadPlaceDetails(placeId)
    }

    val selectedPlace by viewModel.selectedPlace.collectAsStateWithLifecycle()

    RecommendationDetails(
        modifier = Modifier
            .testTag(stringResource(R.string.tag_recommendation_details)),
        selectedPlace = selectedPlace
    )
}

@Composable
fun RecommendationDetails(modifier: Modifier, selectedPlace: Place?) {
    if (selectedPlace != null) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small)),
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(selectedPlace.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedPlace.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            )

           Column(
               modifier = Modifier
                   .weight(0.5f)
                   .padding(
                       top = dimensionResource(R.dimen.padding_small)
                   )
           ) {
               Text(
                   text = selectedPlace.name,
                   style = MaterialTheme.typography.titleMedium
               )

               Text(
                   text = "Rating: ${selectedPlace.rating}",
                   style = MaterialTheme.typography.bodyMedium
               )

               Text(
                   text = "Address: ${selectedPlace.address}",
                   style = MaterialTheme.typography.bodyMedium
               )

               Text(
                   modifier = Modifier.testTag(stringResource(R.string.tag_description)),
                   text = selectedPlace.description,
                   style = MaterialTheme.typography.bodyMedium
               )
           }


        }
    }
}

@Composable
fun RecommendationList(
    modifier: Modifier,
    recommendations: List<Place>,
    contentType: MyCityAppContentType,
    onRecommendationListItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .fillMaxHeight()
            .testTag(stringResource(R.string.tag_recommendation_list))
    ) {
        items(recommendations) { recommendation ->
            RecommendationListItem(recommendation, contentType, onRecommendationListItemClick)
        }
    }
}

@Composable
fun RecommendationListItem(
    place: Place,
    contentType: MyCityAppContentType,
    onRecommendationListItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.padding_medium)
        ),
        onClick = { onRecommendationListItemClick(place.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(place.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.3f)
                    .size(dimensionResource(R.dimen.image_size_small)),
            )

            Text(
                text = place.name,
                modifier = Modifier
                    .weight(0.7f)
                    .padding(
                        start = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_small)
                    ),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.inverseSurface,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendationListPreview() {
    MyCityAppTheme {
        Surface {
            RecommendationList(
                modifier = Modifier,
                contentType = MyCityAppContentType.LIST_ONLY,
                recommendations = LocalDataProvider.getPlacesByCategory(LocalDataProvider.getCategories()[0].id),
                onRecommendationListItemClick = {}
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun RecommendationListAndDetailsPreview() {
    val recommendations = LocalDataProvider.getPlacesByCategory(LocalDataProvider.defaultCategory.id)
    RecommendationListAndDetails(
        modifier = Modifier,
        uiState = RecommendationsUiState(
            recommendations = recommendations,
            selectedPlace = recommendations.firstOrNull()
        ),
        contentType = MyCityAppContentType.LIST_AND_DETAIL,
        onRecommendationListItemClick = {  },
    )
}
package com.example.mycityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.mycityapp.R
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.model.Category
import com.example.mycityapp.ui.theme.MyCityAppTheme
import com.example.mycityapp.ui.utils.MyCityAppContentType

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    contentType: MyCityAppContentType,
    onCategoryListItemClick: (String) -> Unit
) {

    val viewModel: CategoriesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    CategoryList(
        modifier = modifier,
        categories = uiState.categories,
        onCategoryListItemClick = onCategoryListItemClick,
        contentType = contentType
    )

}

@Composable
private fun CategoryList(
    modifier: Modifier,
    categories: List<Category>,
    onCategoryListItemClick: (String) -> Unit,
    contentType: MyCityAppContentType
) {
    LazyColumn(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .fillMaxHeight(),
    ) {
        items(categories) { category ->
            CategoryListItem(category, onCategoryListItemClick)
        }
    }
}

@Composable
fun CategoryListItem(category: Category, onCategoryListItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.padding_medium),
            end = dimensionResource(R.dimen.padding_medium),
            top = dimensionResource(R.dimen.padding_medium)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.padding_medium)
        ),
        onClick = {
            onCategoryListItemClick(category.id)
        }
        ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(category.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = category.name,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size_small))
                    .weight(0.3f),
                contentScale = ContentScale.Crop
            )

            Text(
                text = category.name,
                modifier = Modifier
                    .weight(0.7f)
                    .padding(dimensionResource(R.dimen.padding_small)),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
private fun CategoryListPreview() {
    MyCityAppTheme {
        Surface {
            CategoryList(
                modifier = Modifier,
                categories = LocalDataProvider.getCategories(),
                contentType = MyCityAppContentType.LIST_ONLY,
                onCategoryListItemClick = {},
            )
        }
    }
}
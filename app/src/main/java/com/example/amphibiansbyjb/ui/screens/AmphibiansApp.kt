package com.example.amphibiansbyjb.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.example.amphibiansbyjb.model.AmphibiansModel
import com.example.amphibiansbyjb.ui.AmphibiansUiState
import com.example.amphibiansbyjb.ui.AmphibiansViewModel
import com.example.amphibiansbyjb.ui.theme.AmphibiansTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() // Hides App Bar when scrolling

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AmphibiansTopBar(scrollBehavior = scrollBehavior)
        }
    ) {
        Surface {
            HomeScreen(
                uiState = amphibiansViewModel.uiState,
                contentPadding = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Amphibians",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: AmphibiansUiState,
    contentPadding: PaddingValues,
) {
    when (uiState) {
        is AmphibiansUiState.Loading -> Text(
            text = "Sorry, still Loading!",
            modifier = Modifier.padding(contentPadding)
        )
        is AmphibiansUiState.Success -> SuccessScreen(
            amphibians = uiState.amphibians,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun SuccessScreen(
    amphibians: List<AmphibiansModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = amphibians, key = {amphibian -> amphibian.name}) {
                amphibian ->
            Card(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = amphibian.name,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(amphibian.imrSrc)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                        Text(
                            text = amphibian.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {

    }
}

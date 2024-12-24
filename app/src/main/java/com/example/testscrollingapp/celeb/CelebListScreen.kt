package com.example.testscrollingapp.celeb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testscrollingapp.R
import com.example.testscrollingapp.repository.model.Celebrity
import com.example.testscrollingapp.ui.theme.DarkBlue
import com.example.testscrollingapp.ui.theme.DesertWhite


@Composable
fun CelebListScreenRoot(
    viewModel: CelebListScreenViewModel,
    onAction: (Celebrity) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CelebListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is CelebListAction.OnCelebClick -> onAction(action.celebrity)
                else -> Unit
            }
            viewModel.onCelebAction(action)
        }
    )
}

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}

@Composable
@Preview(showBackground = true)
fun CelebListScreen(
    state: CelebListScreenState = CelebListScreenState(isLoading = true, searchQuery = "ashish"),
    onAction: (CelebListAction) -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResultsListState = rememberLazyListState()
    LaunchedEffect(state.searchResults) {
        searchResultsListState.animateScrollToItem(0)
    }

    val reachedBottom: Boolean by remember {
        derivedStateOf { searchResultsListState.reachedBottom() }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) onAction(CelebListAction.LoadMore)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CelebSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(CelebListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(20.dp))
                } else {
                    when {
                        state.errorMessage != null -> {
                            Text(
                                text = state.errorMessage.asString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        state.searchResults.isEmpty() -> {
                            Text(
                                text = stringResource(R.string.no_search_results),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        else -> {
                            CelebList(
                                books = state.searchResults,
                                onPersonClick = {
                                    onAction(CelebListAction.OnCelebClick(it))
                                },
                                modifier = Modifier.fillMaxSize().padding(top= 20.dp),
                                scrollState = searchResultsListState
                            )
                        }
                    }
                }
            }
        }
    }

}


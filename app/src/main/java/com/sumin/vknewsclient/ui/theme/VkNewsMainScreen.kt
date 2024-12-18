package com.sumin.vknewsclient.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sumin.vknewsclient.MainViewModel
import com.sumin.vknewsclient.domain.FeedPost

@Composable
fun MainScreen(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }
                val items =
                    listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)

                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) {
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())
        PostCard(
            modifier = Modifier.padding(8.dp),
            feedPost = feedPost.value,
            onShareClickListener = viewModel::updateCount, //reference method
            onViewsClickListener = {
                viewModel.updateCount(it)
            },
            onLikeClickListener = {
                viewModel.updateCount(it)
            },
            onCommentClickListener = {
                viewModel.updateCount(it)
            }
        )
    }
}
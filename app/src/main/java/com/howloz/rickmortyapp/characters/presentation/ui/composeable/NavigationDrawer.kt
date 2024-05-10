package com.howloz.rickmortyapp.characters.presentation.ui.composeable

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.howloz.rickmortyapp.R
import com.howloz.rickmortyapp.characters.presentation.ui.theme.RickmortyAppTheme
import kotlinx.coroutines.launch

data class MenuItem(val title: String, val icon: ImageVector)
@Composable
fun NavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    val items = listOf(
        MenuItem(
            title = stringResource(id = R.string.home),
            icon = Icons.Default.Home
        ),
         MenuItem(
            title = stringResource(id = R.string.favorite),
            icon = Icons.Default.Favorite
        ),
         MenuItem(
            title = stringResource(id = R.string.profile),
            icon = Icons.Default.AccountCircle
        ),

    )

    val selectedItem = remember { mutableStateOf(items[0]) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MyTopAppBar(onMenuClick = {
                scope.launch {
                    if(drawerState.isClosed){
                        drawerState.open()
                    }else{
                        drawerState.close()
                    }
                }
            })
        }

    ) {paddingValues ->

        ModalNavigationDrawer(
            modifier = Modifier.padding(paddingValues),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    items.forEach {item ->
                        NavigationDrawerItem(
                            icon={Icon(item.icon,contentDescription = null)},
                            label = { Text(text = item.title)},
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    val snackbarResult = snackbarHostState.showSnackbar(
                                        message = item.title,
                                        actionLabel = context.resources.getString(R.string.subscribe_question),
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )
                                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                                        Toast.makeText(
                                            context,
                                            context.resources.getString(R.string.subscribed_info),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }
                                selectedItem.value = item
                            },
                            modifier = Modifier.padding(horizontal=12.dp))
                    }
//                    Text(stringResource(id = R.string.hello_world))
                }

            }, content = {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                    contentAlignment = Alignment.Center){
                    Text(text = stringResource(id = R.string.hello_world))
                }
            })


    }
}


@Composable
fun BackpressedHandler(
    enabled:Boolean=true,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(onBackPressed)
    val backCallback = remember {
        object : OnBackPressedCallback(enabled){
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current){
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner,backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner,backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onMenuClick : () -> Unit) {
    TopAppBar(
        navigationIcon = {
                         IconButton(onClick = {onMenuClick()}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(id = R.string.menu)
                            )
                         }
        },
        title = {
            Text(stringResource(id = R.string.app_name))
        })
}
@Preview(showBackground = true)
@Composable
private fun NavigationDrawerPreview() {
    RickmortyAppTheme {
        NavigationDrawer()
    }
}
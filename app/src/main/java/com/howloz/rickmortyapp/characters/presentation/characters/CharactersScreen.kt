package com.howloz.rickmortyapp.characters.presentation.characters

import  androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CharactersScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    onCharacterClick : (String)->Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold {paddingValue ->

        LazyColumn(
            contentPadding = paddingValue
        ) {

            items(state.characterList){character ->
                ListItem(
                    headlineContent = {
                        Text(text= character.name.toString())
                    },
                    supportingContent = {
                        Text(text = character.status.toString())
                    },
                    leadingContent = {
                        AsyncImage(model = ImageRequest
                            .Builder(context)
                            .data(character.image)
                            .crossfade(true)
                            .build()
                            , contentDescription =null,
                            modifier=Modifier.size(50.dp))
                    }, modifier = Modifier.clickable {
                        onCharacterClick(character.id.toString())
                    }
                )
            }
        }

    }
}
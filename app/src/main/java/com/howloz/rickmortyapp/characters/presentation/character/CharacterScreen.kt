package com.howloz.rickmortyapp.characters.presentation.character

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.howloz.rickmortyapp.characters.presentation.theme.RickmortyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
       topBar = {
           CenterAlignedTopAppBar(
               title = {},
               navigationIcon = {
                   IconButton(onClick = navigateBack) {
                       Icon(imageVector = Icons.Rounded.ArrowBack,
                           contentDescription =null )
                   }
               }
           )
       }
    ) {padding ->


        Column(
            modifier = Modifier.padding(padding)
        ) {
            AsyncImage(model =
                ImageRequest
                    .Builder(context)
                    .data(state.character.image)
                    .crossfade(true)
                    .build(), contentDescription = null,
                modifier = Modifier.size(250.dp))


            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                )
            ) {
                AsyncImage(model = ImageRequest
                    .Builder(context)
                    .data(state?.character?.image)
                    .crossfade(true)
                    .build(), contentDescription =null,
                    modifier = Modifier.size(250.dp))
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 15.dp
                    )
                ) {

                    Text(text = state.character.name.orEmpty(),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Row(){
                    Text(text = state.character.status.toString())
                }
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterPreview() {
    RickmortyAppTheme {
//        CharacterScreen({})
    }
}
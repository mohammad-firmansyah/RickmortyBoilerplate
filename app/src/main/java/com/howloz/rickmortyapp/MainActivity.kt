package com.howloz.rickmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.howloz.core.utils.Route
import com.howloz.rickmortyapp.characters.presentation.character.CharacterScreen
import com.howloz.rickmortyapp.characters.presentation.character.CharacterViewModel
import com.howloz.rickmortyapp.characters.presentation.characters.CharactersScreen
import com.howloz.rickmortyapp.characters.presentation.theme.RickmortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickmortyAppTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = Route.characterList){
                    composable(Route.characterList){
                        CharactersScreen(
                            onCharacterClick = {id->
                                navController.navigate(
                                    Route.character
                                        .replace("{id}",id)
                                )
                            }
                        )
                    }

                    composable(Route.character){
                        CharacterScreen(
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

            }
        }
    }
}


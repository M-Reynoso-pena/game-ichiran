package com.example.test

import com.example.test.CaliforniaParkDao
import com.example.test.AppDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.test.CaliforniaPark
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Surface(
                    color = if (isDarkTheme()) Color.Black else Color.White, // Set background color based on theme mode
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyNavHost(navController = navController, starDest = "Greeting")
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    val context = LocalContext.current
    val jeux = remember { mutableStateListOf<CaliforniaPark>() }

    /*(Unit) {
        jeux.addAll(AppDatabase.getDatabase(context).californiaParkDao().getAll())
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(jeux) { park ->
                Text(text = park.name)
            }
        }
    }*/
}

//Verifie le theme du telephone(dark/light)
@Composable
fun isDarkTheme(): Boolean {
    return isSystemInDarkTheme()
}

//Affiche la colonne d'elements
@Composable
fun Greeting(modifier: Modifier = Modifier, navController: NavHostController) {
    MaterialTheme {
        Surface(modifier = modifier,
            color = if (isDarkTheme()) Color.Black else Color.White
        ) {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(20) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        //horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Bouton pour acceder a la page du jeu
                        Button(onClick = {
                            navController.navigate("NextScreen")
                        },
                            colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isDarkTheme()) Color.Black else Color.White,
                            contentColor = if (isDarkTheme()) Color.White else Color.Black),
                            elevation = ButtonDefaults.elevation(0.dp, 0.dp)
                        ){
                            Image(
                                painter = if(index==0) painterResource(id = R.drawable.mario)
                                else if(index == 1) painterResource(id = R.drawable.sonic)
                                else painterResource(id = R.drawable.super_mario_bros),
                                contentDescription = "image jeu",
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))


                            Text(text = if(index==0)"Mario Odyssey" else "Titre Jeu ${index + 1}",
                                color = if (isDarkTheme()) Color.White else Color.Black
                            )
                            Column{
                                var prix = 20.99
                                var devise = "€"
                                var etat = 0
                                Text(text = if(index-((index/2)*2) == 1) "\t\t${round((prix + index)*100)/100} ${devise}" else "\t\tacheté", fontSize = 10.sp)
                            }
                        }


                    }
                }
            }
        }
    }
}

@Composable
//ecran du jeu
fun GameScreen(navController: NavHostController) {
    var prix = 20.99
    var vie = 60
    var studio : String = "Nintendo"
    var config = 1
    var console = 1
    var plateforme = 1
    var speedrunable : Boolean = true
    var devise = "€"
    var etat = 0
    var titre : String = "Mario Odysssey"
    var genre =  "Aventure"
    Column{

        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$titre", color = if (isDarkTheme()) Color.White else Color.Black,fontSize = 20.sp )
        }
        Image(painter = painterResource(id = R.drawable.mario), contentDescription = "image du jeu" )
        Text(text = "Genre: $genre", color = if (isDarkTheme()) Color.White else Color.Black,fontSize = 12.sp )
        Text(text = if(etat== 0) "Prix: ${prix} ${devise}" else "acheté", fontSize = 12.sp,color = if (isDarkTheme()) Color.White else Color.Black)
        Text(text = "Durée de vie: ${vie}",color = if (isDarkTheme()) Color.White else Color.Black,fontSize = 12.sp)
        Text(text = "Studio: $studio",color = if (isDarkTheme()) Color.White else Color.Black,fontSize = 12.sp)
        Text(text = if(config== 0) "Config: Manette" else "Config: Clavier", fontSize = 12.sp,color = if (isDarkTheme()) Color.White else Color.Black)
        Text(text = if(plateforme== 0) "Plateforme: PC" else "Plateforme: Switch", fontSize = 12.sp,color = if (isDarkTheme()) Color.White else Color.Black)
        Text(text = if(speedrunable) "Speedrunable: Oui" else "Speedrunable: Non", fontSize = 12.sp,color = if (isDarkTheme()) Color.White else Color.Black)
        Text(text = "Description:\nDescription du jeu.",fontSize = 12.sp,color = if (isDarkTheme()) Color.White else Color.Black)

        Button(onClick = {
            navController.navigate("Greeting")
        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isDarkTheme()) Color.Black else Color.White,
            contentColor = if (isDarkTheme()) Color.White else Color.Black))
        {
            Text(text = "Retour")
        }
    }

}

//Permet de naviguer entre les ecrans
@Composable
fun MyNavHost(modifier: Modifier = Modifier, navController: NavHostController, starDest: String) {
    NavHost(navController = navController, startDestination = starDest) {
        composable(route = "Greeting") {
            Greeting(Modifier.padding(16.dp), navController = navController)
        }
        composable(route = "NextScreen") {
            GameScreen(navController = navController)
        }
        composable(route = "Server"){
            AppContent()
        }
    }
}




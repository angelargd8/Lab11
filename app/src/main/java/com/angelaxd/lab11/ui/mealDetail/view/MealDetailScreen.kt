package com.angelaxd.lab11.ui.mealDetail.view


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.angelaxd.lab11.navigation.AppScreens
import com.angelaxd.lab11.networking.dto.Meal
import com.angelaxd.lab11.networking.state.DataStateMeal
import com.angelaxd.lab11.ui.categories.view.CardItem
import com.angelaxd.lab11.ui.mealDetail.viewmodel.MealDetailViewmodel
import com.angelaxd.lab11.ui.objetos.Texto
import com.angelaxd.lab11.ui.objetos.TopBar


@Composable
fun MealDetailScreen(
    navController: NavHostController,
    name: String?,
    id: String?, // ?=opcional,
    mealDetailViewmodel: MealDetailViewmodel

){
    when(val result = mealDetailViewmodel.response.value){
        is DataStateMeal.LoadingMeal -> {
            LoadingMeal()
        }
        is  DataStateMeal.SuccessMeal -> {
            EstructuraMeal(navController, result.data, name, mealDetailViewmodel)
        }
        is  DataStateMeal.FailureMeal-> {
            ErrorMeal(result)
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){

                Text(
                    text = "ERROR",
                    fontSize = 15.sp,
                    color = Color(0xFFE91E63),
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
                )
            }
        }

    }




}

@Composable
fun LoadingMeal() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMeal(result: DataStateMeal.FailureMeal) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        Text(
            text = result.message,
            fontSize = 15.sp,
            color = Color(0xFFE91E63),
            overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
        )
    }

}


@Composable
fun LazyColumnListMeal(
    navController: NavHostController,
    data: MutableList<Meal>
) {
    LazyColumn(){
        items(data){ meal ->
            //mandarlo a la card
            CardItemMeal(navController,meal)
        }
    }


}

@Composable
fun CardItemMeal(
    navController: NavHostController,
    meal: Meal
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(AppScreens.MealsScreen.createRoute(meal.idMeal!!)) {
                launchSingleTop = true
            } }
    ) {
        //contenido
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.strMealThumb!!)
                    .transformations()
                    .size(299,299)
                    .build()
                ,
                contentDescription = "imagen jaja",
                modifier= Modifier.fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                androidx.compose.material3.Text(
                    text = meal.strMeal!!,
                    fontSize = 15.sp,
                    color = Color(0xFF6200EE),
                    fontWeight = FontWeight.Bold, //grosor del texto
                    fontStyle = FontStyle.Italic, //estilo (normal, cursiva..)
                    lineHeight = 20.sp, //altura de linea del texto
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
                )
                Spacer(modifier =Modifier.height(2.dp))

            }
        }


    }

}


@Composable
fun EstructuraMeal(
    navController: NavHostController,
    data: MutableList<Meal>,
    name: String?,
    mealDetailViewmodel: MealDetailViewmodel
) {
    Column {
        TopBar(navController, "Meal Detail")

        name.let {
            Texto(texto = name.toString())
            if (name == "Seafood") {
                //LAZY COLUMN
                LazyColumnListMeal(navController, data)

            } else {
                Text(
                    text = "  no hay información acerca de esta categoria",
                    fontSize = 15.sp,
                    color = Color(0xFFE91E63),
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
                )
            }


        }


    }
}




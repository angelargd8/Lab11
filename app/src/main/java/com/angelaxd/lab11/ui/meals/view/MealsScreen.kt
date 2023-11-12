package com.angelaxd.lab11.ui.meals.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.angelaxd.lab11.networking.dto.MealX
import com.angelaxd.lab11.networking.state.DataStateX
import com.angelaxd.lab11.ui.meals.viewmodel.MealViewmodel
import com.angelaxd.lab11.ui.objetos.Texto
import com.angelaxd.lab11.ui.objetos.TextoDescripcion
import com.angelaxd.lab11.ui.objetos.TopBar

@Composable
fun mealsScreen(
    navController: NavHostController,
    id: String?,
    mealViewmodel: MealViewmodel

){
    when(val result = mealViewmodel.response.value){
        is DataStateX.LoadingX -> {
            LoadingX()
        }
        is  DataStateX.SuccessX -> {
            EstructuraX(navController, result.data, id, mealViewmodel)
        }
        is  DataStateX.FailureX-> {
            ErrorX(result)
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
fun LoadingX() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorX(result: DataStateX.FailureX) {
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
fun EstructuraX(navController: NavHostController, data: MutableList<MealX>, id: String?, mealViewmodel: MealViewmodel) {

    Column {
        TopBar(navController, "Meal Detail")

        id?.let {
            //Texto(texto = id.toString())
            if (id == "52944") {
                LazyColumn(){
                    data?.let {
                        items(data){
                            meal->
                            cardMeal(meal)
                        }
                    }
                }


            } else {
                Text(
                    text = "  no hay información acerca de este meal",
                    fontSize = 15.sp,
                    color = Color(0xFFE91E63),
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
                )
            }


        }


    }

}

@Composable
fun cardMeal(meal: MealX) {
    Column {

        Card {
            Column {
                Texto(texto = meal.strMeal!!)
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.strMealThumb!!)
                        .transformations()
                        //.size(1199,1199)
                        .build()
                    ,
                    contentDescription = "imagen jaja",
                    modifier= Modifier.fillMaxWidth()
                )

                TextoDescripcion("Area: "+meal.strArea!!)
                TextoDescripcion("Preparacion: ")
                TextoDescripcion(meal.strInstructions!!)
                TextoDescripcion("Ingredientes: ")
                TextoDescripcion(meal.strIngredient1!!)
                TextoDescripcion(meal.strIngredient2!!)
                TextoDescripcion(meal.strIngredient3!!)
                TextoDescripcion(meal.strIngredient4!!)
                TextoDescripcion(meal.strIngredient5!!)
                TextoDescripcion(meal.strIngredient6!!)
                TextoDescripcion(meal.strIngredient7!!)
                TextoDescripcion(meal.strIngredient8!!)
                TextoDescripcion(meal.strIngredient9!!)
                TextoDescripcion(meal.strIngredient10!!)

                }
        }
    }
}



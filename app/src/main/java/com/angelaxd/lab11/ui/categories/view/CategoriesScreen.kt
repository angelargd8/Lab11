package com.angelaxd.lab11.ui.categories.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material3.CardDefaults
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
import com.angelaxd.lab11.networking.dto.Category
import com.angelaxd.lab11.networking.state.DataStateCategories
import com.angelaxd.lab11.ui.categories.viewmodel.CategoryViewmodel
import com.angelaxd.lab11.ui.objetos.TopBar


@Composable
fun CategoriesScreen(navController: NavHostController, categoryViewmodel: CategoryViewmodel) {

    when(val result = categoryViewmodel.response.value){
        is DataStateCategories.LoadingCategories -> {
            Loading()
        }
        is  DataStateCategories.SuccessCategories -> {
            Estructura(navController, result.data)
        }
        is  DataStateCategories.FailureCategories-> {
            Error(result)
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
fun Loading() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun Error(result: DataStateCategories.FailureCategories) {
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
fun Estructura(navController: NavHostController, data: MutableList<Category>) {

    Column {
        TopBar(navController,"Categories" )
        //LAZY COLUMN
        LazyColumnList(navController, data)

    }

}

@Composable
fun LazyColumnList(navController: NavHostController, data: MutableList<Category>) {
    LazyColumn(){

        data?.let {
            items(data){
                    category ->
                CardItem(navController, category)
            }

        }


    }
}

@Composable
fun CardItem(navController: NavHostController, category: Category) {

    val name: String?  = category.strCategory
    val id: String? = category.idCategory
    val description: String? = category.strCategoryDescription
    val thumb: String? = category.strCategoryThumb

    Card(

        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            //.background(Color(0xE6DF86EE))
            .clickable {
                       navController.navigate(AppScreens.MealDetailScreen.createRoute(name!!,id!!)) {
                           launchSingleTop = true
                       }


            },
        shape= RoundedCornerShape(6.dp),
        //contentColor = contentColorFor(Color(0xFF9C27B0))
            ){
        //
        Spacer(modifier =Modifier.height(2.dp))
        Row{
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(thumb)
                .transformations()
                .size(499,499)
                .build()
                , contentDescription = "imagen jaja")

            Spacer(modifier =Modifier.height(2.dp))
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = name!!,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold, //grosor del texto
                    fontStyle = FontStyle.Italic, //estilo (normal, cursiva..)
                    color = Color(0xFF6200EE),
                    lineHeight = 20.sp, //altura de linea del texto
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento
                )
                Spacer(modifier =Modifier.height(2.dp))
                Text(
                    text = description!!,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold, //grosor del texto
                    fontStyle = FontStyle.Normal, //estilo (normal, cursiva..)
                    lineHeight = 13.sp, //altura de linea del texto
                    overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento

                )

            }
        }


    }



}

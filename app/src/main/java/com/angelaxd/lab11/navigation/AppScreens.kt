package com.angelaxd.lab11.navigation

sealed class AppScreens(val route: String){
    object CategoriesScreen: AppScreens("primera")

    //object CategoryDetail: AppScreens("segunda/{category}"){
      //  fun createRoute(category: String) = "segunda/$category"
    //}

    object MealDetailScreen: AppScreens("segunda/{category}/{id}"){
        fun createRoute(category: String, id: String) = "segunda/$category/$id"
    }
    //idFilter
    object MealsScreen: AppScreens("cuarta/{idFilter}"){
        fun createRoute(id: String) = "cuarta/$id"
    }
}

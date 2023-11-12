package com.angelaxd.lab11.di

import androidx.lifecycle.ViewModel
import com.angelaxd.lab11.ui.categories.viewmodel.CategoryViewmodel
import com.angelaxd.lab11.ui.mealDetail.viewmodel.MealDetailViewmodel
import com.angelaxd.lab11.ui.meals.viewmodel.MealViewmodel
/**
    di -> injeccion de dependencias

    algo que nos regresa objetos de cierto tipo
    que se va a usar en varios lugares,
    para no crear toodo varias veces

 * **/


class SharedViewModel: ViewModel() {

    val CategoryViewmodel: CategoryViewmodel = CategoryViewmodel()

    val MealViewmodel: MealViewmodel = MealViewmodel()

    val MealDetailViewmodel: MealDetailViewmodel = MealDetailViewmodel()

}
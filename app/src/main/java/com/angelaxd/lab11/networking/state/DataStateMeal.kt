package com.angelaxd.lab11.networking.state

import com.angelaxd.lab11.networking.dto.Category
import com.angelaxd.lab11.networking.dto.Meal

sealed class DataStateMeal{
    class SuccessMeal(val data: MutableList<Meal>): DataStateMeal()
    class FailureMeal(val message: String): DataStateMeal()
    object LoadingMeal: DataStateMeal()
    object EmptyMeal: DataStateMeal()
}


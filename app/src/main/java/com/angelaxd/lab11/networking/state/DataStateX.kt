package com.angelaxd.lab11.networking.state

import com.angelaxd.lab11.networking.dto.MealX

sealed class DataStateX{
    class SuccessX(val data: MutableList<MealX>): DataStateX()
    class FailureX(val message: String): DataStateX()
    object LoadingX: DataStateX()
    object EmptyX : DataStateX()
}


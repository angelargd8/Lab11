package com.angelaxd.lab11.networking.state

import com.angelaxd.lab11.networking.dto.Category

sealed class DataStateCategories{
    class SuccessCategories(val data: MutableList<Category>): DataStateCategories()
    class FailureCategories(val message: String): DataStateCategories()
    object LoadingCategories: DataStateCategories()
    object EmptyCategories : DataStateCategories()
}

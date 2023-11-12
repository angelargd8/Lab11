package com.angelaxd.lab11.ui.mealDetail.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.angelaxd.lab11.networking.dto.Category
import com.angelaxd.lab11.networking.dto.Meal
import com.angelaxd.lab11.networking.state.DataStateCategories
import com.angelaxd.lab11.networking.state.DataStateMeal
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MealDetailViewmodel {

    //por ahora esta vacio, unas lineas más y ya cambia
    val response: MutableState<DataStateMeal> = mutableStateOf(DataStateMeal.EmptyMeal)

    //inicializa
    init {

        fetchDataFromFirebase()
    }

    //traer la data de Firebase
    private fun fetchDataFromFirebase(){
        val tempList = mutableListOf<Meal>()
        //estado de cargar la data
        response.value = DataStateMeal.LoadingMeal

        //obtener la data de Firebase y ponerla en el mutable list
        FirebaseDatabase.getInstance().getReference("mealsFilter/meals").addListenerForSingleValueEvent(object:
            ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for(DataSnap in snapshot.children){
                    val MealItem = DataSnap.getValue(Meal::class.java)
                    if(MealItem != null)
                        tempList.add(MealItem)
                }
                //estado de exito
                response.value = DataStateMeal.SuccessMeal(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                //estado de error
                response.value = DataStateMeal.FailureMeal(error.message)
            }

        })
    }


}
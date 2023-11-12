package com.angelaxd.lab11.ui.meals.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.angelaxd.lab11.networking.dto.Meal
import com.angelaxd.lab11.networking.dto.MealX
import com.angelaxd.lab11.networking.state.DataStateMeal
import com.angelaxd.lab11.networking.state.DataStateX
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MealViewmodel: ViewModel(){

    //por ahora esta vacio, unas lineas más y ya cambia
    val response: MutableState<DataStateX> = mutableStateOf(DataStateX.EmptyX)

    //inicializa
    init {

        fetchDataFromFirebase()
    }

    //traer la data de Firebase
    private fun fetchDataFromFirebase(){
        val tempList = mutableListOf<MealX>()
        //estado de cargar la data
        response.value = DataStateX.LoadingX

        //obtener la data de Firebase y ponerla en el mutable list
        FirebaseDatabase.getInstance().getReference("meal/meals").addListenerForSingleValueEvent(object:
            ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for(DataSnap in snapshot.children){
                    val MealItem = DataSnap.getValue(MealX::class.java)
                    if(MealItem != null)
                        tempList.add(MealItem)
                }
                //estado de exito
                response.value = DataStateX.SuccessX(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                //estado de error
                response.value = DataStateX.FailureX(error.message)
            }

        })
    }

}
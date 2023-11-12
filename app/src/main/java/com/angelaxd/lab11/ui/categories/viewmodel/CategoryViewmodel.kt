package com.angelaxd.lab11.ui.categories.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.angelaxd.lab11.networking.dto.Category
import com.angelaxd.lab11.networking.state.DataStateCategories
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Data de Categories
 * */

class CategoryViewmodel: ViewModel(){
    //por ahora esta vacio, unas lineas más y ya cambia
    val response: MutableState<DataStateCategories> = mutableStateOf(DataStateCategories.EmptyCategories)

    //inicializa
    init {

        fetchDataFromFirebase()
    }

    //traer la data de Firebase
    private fun fetchDataFromFirebase(){
        val tempList = mutableListOf<Category>()
        //estado de cargar la data
        response.value = DataStateCategories.LoadingCategories

        //obtener la data de Firebase y ponerla en el mutable list
        FirebaseDatabase.getInstance().getReference("categories").addListenerForSingleValueEvent(object:
            ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for(DataSnap in snapshot.children){
                    val categoryItem = DataSnap.getValue(Category::class.java)
                    if(categoryItem != null)
                        tempList.add(categoryItem)
                }
                //estado de exito
                response.value = DataStateCategories.SuccessCategories(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                //estado de error
                response.value = DataStateCategories.FailureCategories(error.message)
            }

        })
    }


}
package com.github.vivaeltaco.model

class Food : ArrayList<Food.ItemFood>() {

    data class ItemFood(
        val id : Int,
        val name : String,
        val info : String,
        val picture : String,
        val price : String,
        val type : String,
        val icon : String,



        )
}
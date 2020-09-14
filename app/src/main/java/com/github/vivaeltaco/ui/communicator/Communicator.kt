package com.github.vivaeltaco.ui.communicator

interface Communicator {

    fun getFood(type: String)
    fun getAddress(address: String)
    fun foodFragment(picture: String, nameFood: String, price: String, infoFood: String)
    fun gotoLocation(lat : Double, long: Double)
}
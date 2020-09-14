package com.github.vivaeltaco.model

class Address : ArrayList<Address.AddressItem>(){
    data class AddressItem(
        val address: String,
        val id: String,
        val latitude: String,
        val longitude: String,
        val picture: String,
        val schedule: String,
        val state: String
    )
}
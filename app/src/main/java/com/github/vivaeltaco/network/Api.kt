package com.github.vivaeltaco.network

import com.github.vivaeltaco.model.Address
import com.github.vivaeltaco.model.Food
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {


    @FormUrlEncoded
    @POST("rigo/vivaeltaco/food.php")
    fun getFood(
        @Field("foodType") type : String
    ): Observable<Food>


    @FormUrlEncoded
    @POST("rigo/vivaeltaco/address.php")
    fun getAddress(
        @Field("address") address: String
    ): Observable<Address>
}
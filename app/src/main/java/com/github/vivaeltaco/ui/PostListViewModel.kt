package com.github.vivaeltaco.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vivaeltaco.model.Address
import com.github.vivaeltaco.model.Food
import com.github.vivaeltaco.network.Api
import com.github.vivaeltaco.viewmodel.MyViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class PostListViewModel: MyViewModel() {

    val TAG : String = "TAG"
    @Inject
    lateinit var api: Api
    var foodLiveData = MutableLiveData<Food>()
    var errorLiveData = MutableLiveData<Boolean>()
    var addressLiveData = MutableLiveData<Address>()
    var loadingLiveData = MutableLiveData<Boolean>()


    @Throws(InterruptedException::class, IOException::class)
    fun isConnected(): Boolean{
        val command ="ping -c 1 nonstopcode.com"
        return Runtime.getRuntime().exec(command).waitFor()==0
    }

    fun getFood(type : String){
            api.getFood(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Food> {
                    override fun onSubscribe(d: Disposable?) {
                        setLoadingLiveData(true)
                    }

                    override fun onNext(t: Food?) {
                        setFoodLiveData(t!!)
                    }

                    override fun onError(e: Throwable?) {
                        setErrorLiveData(false)
                        Log.d(TAG, "onError: ${e?.message.toString()}")
                    }

                    override fun onComplete() {
                        setLoadingLiveData(false)

                    }
                })

    }
    fun getAddress(address : String){
            api.getAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Address> {
                    override fun onSubscribe(d: Disposable?) {
                        setLoadingLiveData(false)
                    }

                    override fun onNext(t: Address?) {
                        setAddressLiveData(t!!)
                        Log.d(TAG, "address: $t")
                    }

                    override fun onError(e: Throwable?) {
                        setErrorLiveData(false)
                        Log.d(TAG, "onError: ${e?.message.toString()}")
                    }

                    override fun onComplete() {
                        setLoadingLiveData(true)
                    }
                })

    }

    fun setFoodLiveData(items : Food){
        foodLiveData.value = items
    }
    fun getFoodLiveData() : LiveData<Food>{
        return foodLiveData
    }

    fun setAddressLiveData(address : Address){
        addressLiveData.value = address
    }
    fun getAddressLiveData() : LiveData<Address>{
        return addressLiveData
    }


    fun setLoadingLiveData(value : Boolean){
        loadingLiveData.value = value
    }
    fun getLoadingLiveData() : LiveData<Boolean>{
        return loadingLiveData
    }

    fun setErrorLiveData(value : Boolean){
        errorLiveData.value = value
    }
    fun getErrorLiveData() : LiveData<Boolean>{
        return errorLiveData
    }
}
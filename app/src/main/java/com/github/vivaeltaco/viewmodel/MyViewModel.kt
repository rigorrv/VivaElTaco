package com.github.vivaeltaco.viewmodel

import androidx.lifecycle.ViewModel
import com.github.vivaeltaco.injection.DaggerViewModelInjection
import com.github.vivaeltaco.injection.NetworkModule
import com.github.vivaeltaco.injection.ViewModelInjection
import com.github.vivaeltaco.ui.PostListViewModel
import com.github.vivaeltaco.ui.PostViewModel

abstract class MyViewModel : ViewModel(){

    val injector : ViewModelInjection = DaggerViewModelInjection
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this){
            is PostViewModel -> injector.inject(this)
            is PostListViewModel-> injector.inject(this)
        }
    }
}
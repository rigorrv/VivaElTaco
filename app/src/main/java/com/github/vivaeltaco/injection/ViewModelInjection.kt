package com.github.vivaeltaco.injection

import android.os.Build
import com.github.vivaeltaco.ui.PostListViewModel
import com.github.vivaeltaco.ui.PostViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjection {

    fun inject (postListViewModel: PostListViewModel)
    fun inject (postViewModel: PostViewModel)

    @Component.Builder
    interface Builder{
        fun build () : ViewModelInjection
        fun networkModule(networkModule : NetworkModule): Builder
    }
}
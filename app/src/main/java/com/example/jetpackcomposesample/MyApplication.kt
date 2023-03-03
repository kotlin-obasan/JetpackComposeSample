package com.example.jetpackcomposesample

import android.app.Application
import com.example.jetpackcomposesample.data.AppContainer
import com.example.jetpackcomposesample.data.AppContainerImpl

class MyApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}

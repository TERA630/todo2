package com.example.yoshi.todo2

import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                this.module
        ))
    }

    private val module: Module = applicationContext {
        factory { MainViewModel() }
    }
}
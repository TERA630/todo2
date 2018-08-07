package com.example.yoshi.todo2
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                myModule
        ))
    }
    private val myModule: Module = applicationContext {
        viewModel { MainViewModel() }
        bean { KeyboardUtils() }
    }
}
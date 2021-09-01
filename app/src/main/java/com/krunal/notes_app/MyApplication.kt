package com.krunal.notes_app

import android.app.Application
import com.krunal.notes_app.DI.AppComponent
import com.krunal.notes_app.DI.AppModule
import com.krunal.notes_app.DI.DaggerAppComponent


class MyApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }


}
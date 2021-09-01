package com.krunal.notes_app.DI

import android.app.Application
import android.content.Context
import com.krunal.notes_app.Database.AppDatabase
import com.krunal.notes_app.Database.NoteDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): NoteDAO {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }
}
package com.krunal.notes_app.DI;

import com.krunal.notes_app.modules.Add_Notes.AddNotesFragment
import com.krunal.notes_app.modules.Edit_Notes.EditNotesFragment
import com.krunal.notes_app.modules.Home.HomeFragment

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: HomeFragment);
    fun inject(activity: AddNotesFragment);
    fun inject(activity: EditNotesFragment);
}
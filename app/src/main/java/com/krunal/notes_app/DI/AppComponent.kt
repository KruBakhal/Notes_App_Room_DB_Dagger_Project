package com.krunal.notes_app.DI;

import com.krunal.notes_app.Modules.Add_Notes.AddNotesFragment
import com.krunal.notes_app.Modules.Edit_Notes.EditNotesFragment
import com.krunal.notes_app.Modules.Home.HomeFragment

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: HomeFragment);
    fun inject(activity: AddNotesFragment);
    fun inject(activity: EditNotesFragment);
}
package com.ankit.ankitkrsingh_reddit.di

import com.ankit.ankitkrsingh_reddit.di.scope.ActivityScoped
import com.ankit.ankitkrsingh_reddit.presentation.MainActivity
import com.ankit.ankitkrsingh_reddit.presentation.MainModule
import com.ankit.ankitkrsingh_reddit.presentation.MainUtilModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
@Module( includes = [ ViewModelModule::class])
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class,MainUtilModule::class])
    abstract fun mainActivity() : MainActivity
}
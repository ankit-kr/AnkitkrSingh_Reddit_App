package com.ankit.ankitkrsingh_reddit.presentation

import android.app.Activity
import com.ankit.ankitkrsingh_reddit.di.scope.ActivityScoped
import com.ankit.ankitkrsingh_reddit.di.scope.FragmentScoped
import com.ankit.ankitkrsingh_reddit.presentation.home.HomeFragment
import com.ankit.ankitkrsingh_reddit.presentation.home.HomeFragmentUtilModule
import com.ankit.ankitkrsingh_reddit.presentation.home.detail.DetailFragment
import com.ankit.ankitkrsingh_reddit.presentation.home.detail.DetailFragmentUtilModule
import com.ankit.ankitkrsingh_reddit.presentation.splash.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
@Module
abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract fun bindActivity(activity: MainActivity): Activity

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun splashFragment() : SplashFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules =  [HomeFragmentUtilModule::class])
    abstract fun homeFragment() : HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [DetailFragmentUtilModule::class])
    abstract fun detailFragment() : DetailFragment

}
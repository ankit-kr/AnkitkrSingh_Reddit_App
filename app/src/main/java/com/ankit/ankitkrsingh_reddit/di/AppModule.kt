package com.ankit.ankitkrsingh_reddit.di

import android.content.Context
import com.ankit.ankitkrsingh_reddit.RedditApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindContext(application: RedditApplication): Context
}
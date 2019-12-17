package com.ankit.ankitkrsingh_reddit.di

import com.ankit.ankitkrsingh_reddit.RedditApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
@Singleton
@Component( modules = [
    AppModule::class,
    AppUtilModule::class,
    NetworkModule::class,
    UseCasesModule::class,
    RepositoryModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent :  AndroidInjector<RedditApplication>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindApplication(application: RedditApplication): Builder

        fun build(): AppComponent
    }
}
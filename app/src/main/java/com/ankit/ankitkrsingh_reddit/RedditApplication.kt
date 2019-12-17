package com.ankit.ankitkrsingh_reddit

import com.ankit.ankitkrsingh_reddit.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
class RedditApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }
}

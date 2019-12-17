package com.ankit.ankitkrsingh_reddit.presentation

import com.ankit.ankitkrsingh_reddit.di.scope.ActivityScoped
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
@Module
class MainUtilModule {

    @ActivityScoped
    @Provides
    fun provideGlideRequestManager(activity: MainActivity): RequestManager {
        return Glide.with(activity).applyDefaultRequestOptions(RequestOptions())
    }

}
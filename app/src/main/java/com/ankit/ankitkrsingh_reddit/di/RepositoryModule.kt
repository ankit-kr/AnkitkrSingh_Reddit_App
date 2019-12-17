package com.ankit.ankitkrsingh_reddit.di

import com.ankit.ankitkrsingh_reddit.data.RedditRepositoryImpl
import com.ankit.ankitkrsingh_reddit.data.RedditRestApi
import com.ankit.ankitkrsingh_reddit.domain.RedditRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRedditRepository(redditRestApi: RedditRestApi): RedditRepository = RedditRepositoryImpl(redditRestApi)


}
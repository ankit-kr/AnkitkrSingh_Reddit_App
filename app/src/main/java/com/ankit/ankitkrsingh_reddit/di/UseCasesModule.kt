package com.ankit.ankitkrsingh_reddit.di

import com.ankit.ankitkrsingh_reddit.domain.RedditRepository
import com.ankit.ankitkrsingh_reddit.domain.RedditUseCaseInteractor
import com.ankit.ankitkrsingh_reddit.domain.RedditUseCases
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
class UseCasesModule {

    @Provides
    fun providesRedditPostUseCases(redditRepository: RedditRepository): RedditUseCases = RedditUseCaseInteractor(redditRepository)

}
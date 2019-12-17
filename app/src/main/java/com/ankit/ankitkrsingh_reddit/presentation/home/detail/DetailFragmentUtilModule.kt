package com.ankit.ankitkrsingh_reddit.presentation.home.detail

import androidx.databinding.ObservableArrayList
import com.ankit.ankitkrsingh_reddit.di.scope.FragmentScoped
import dagger.Module
import dagger.Provides

/**
 *<h1>DetailFragmentUtilModule class</h1>

 *<p><Dagger module/p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
@Module
class DetailFragmentUtilModule {

    @FragmentScoped
    @Provides
    fun provideCommentsList() : ObservableArrayList<Any> {
        return ObservableArrayList()
    }
}
package com.ankit.ankitkrsingh_reddit.presentation.home

import androidx.databinding.ObservableArrayList
import com.ankit.ankitkrsingh_reddit.di.scope.FragmentScoped
import com.ankit.ankitkrsingh_reddit.domain.model.PopularItem
import com.ankit.ankitkrsingh_reddit.presentation.home.adapter.PopularItemAdapter
import dagger.Module
import dagger.Provides

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 15 Dec 2019
 * @version : 1.0
 *
 */
@Module
class HomeFragmentUtilModule {

    @FragmentScoped
    @Provides
    fun providePopularList() : ObservableArrayList<PopularItem>{
        return ObservableArrayList()
    }

}
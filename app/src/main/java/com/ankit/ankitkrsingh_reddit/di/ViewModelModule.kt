package com.ankit.ankitkrsingh_reddit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ankit.ankitkrsingh_reddit.presentation.home.SharedHomeViewModel
import com.ankit.ankitkrsingh_reddit.presentation.splash.SplashViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedHomeViewModel::class)
    abstract fun bindSharedHomeViewModel(viewModel: SharedHomeViewModel):ViewModel
}
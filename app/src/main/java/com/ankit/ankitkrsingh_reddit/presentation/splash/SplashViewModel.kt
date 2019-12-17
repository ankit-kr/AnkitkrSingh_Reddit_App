package com.ankit.ankitkrsingh_reddit.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseViewModel
import com.ankit.ankitkrsingh_reddit.presentation.common.Constants
import com.ankit.ankitkrsingh_reddit.presentation.common.Event
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()

    enum class Route{
        HOME_SCREEN
    }
    private val _routeEvent = MutableLiveData<Event<Route>>()
    val routeEvent : LiveData<Event<Route>> = _routeEvent

    private fun triggerRouteEvent(route: Route){
        _routeEvent.postValue(Event(route))
    }

    fun startSplashTimer() {
        val observer =  Completable.timer(Constants.SPLASH_DELAY_SECOND,TimeUnit.SECONDS).doOnComplete {
            triggerRouteEvent(Route.HOME_SCREEN)
        }.subscribe()
        compositeDisposable.add(observer)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

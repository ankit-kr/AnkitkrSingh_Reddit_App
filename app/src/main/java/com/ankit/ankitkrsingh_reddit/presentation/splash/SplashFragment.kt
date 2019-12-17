package com.ankit.ankitkrsingh_reddit.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseFragment
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory)[SplashViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startSplashTimer()
        setupNavigationObserver()
    }

    private fun setupNavigationObserver() {
        viewModel.routeEvent.observe(viewLifecycleOwner, Observer {
            when(it.getContentIfNotHandled()){
                SplashViewModel.Route.HOME_SCREEN ->{
                    val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            }
        })
    }
}

package com.ankit.ankitkrsingh_reddit.presentation.base

import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
abstract class BaseActivity : DaggerAppCompatActivity(){

    private val navController: NavController by lazy {
        findNavController(getNavControllerId())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    abstract fun getNavControllerId(): Int
}
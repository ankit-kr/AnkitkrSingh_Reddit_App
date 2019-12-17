package com.ankit.ankitkrsingh_reddit.presentation

import android.os.Bundle
import com.ankit.ankitkrsingh_reddit.R
import com.ankit.ankitkrsingh_reddit.presentation.base.BaseActivity

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
class MainActivity : BaseActivity(){

    override fun getNavControllerId(): Int {
        return R.id.activityMainNavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
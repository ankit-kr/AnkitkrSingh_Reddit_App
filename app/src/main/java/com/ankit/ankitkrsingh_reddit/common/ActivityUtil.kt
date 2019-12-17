package com.ankit.ankitkrsingh_reddit.common

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
fun AppCompatActivity.replaceFragment(@IdRes where: Int, fragment: Fragment, tag: String){
    supportFragmentManager.beginTransaction()
        .replace(where,fragment,tag)
        .commit()
}
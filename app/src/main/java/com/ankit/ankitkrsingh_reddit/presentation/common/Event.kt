package com.ankit.ankitkrsingh_reddit.presentation.common

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 14 Dec 2019
 * @version : 1.0
 *
 */
class Event<T> constructor(private val content: T){

    private var handled =  false

    fun getContentIfNotHandled(): T?{
        return if(handled){
            null
        }else{
            handled = true
            content
        }
    }
}
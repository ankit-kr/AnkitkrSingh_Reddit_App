@file:JvmName("Converter")
package com.ankit.ankitkrsingh_reddit.presentation.common

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan


/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 * 
 */

fun getAuthorAndDateFormat(author: String, domain: String, date: Long): Spannable{
    val formatString = "$author . $domain . $date"
    val spanString = SpannableString(formatString)
    spanString.setSpan(ForegroundColorSpan(Color.RED), 0, author.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spanString
}


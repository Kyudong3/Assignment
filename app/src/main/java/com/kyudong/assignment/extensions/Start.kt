package com.kyudong.assignment.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.kyudong.assignment.data.Company
import kotlin.reflect.KClass

fun Context.start(kClass: KClass<out Activity>, company: Company) {
    val intent = Intent(this, kClass.java)
    intent.putExtra("company", company)
    startActivity(intent)
}
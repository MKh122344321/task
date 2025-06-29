package com.example.myapplication

import android.app.Application
import android.content.Context

class ShopApplication :Application() {
init {
    application=this
}
    companion object {
        private  lateinit var application:ShopApplication
        fun getApplicationContext():Context{
            return application.applicationContext
        }
    }
}
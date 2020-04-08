package com.jarroyo.kmp_mvvm_firebase

import androidx.multidex.MultiDexApplication
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs

open class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        InjectorCommon.provideContextArgs(ContextArgs(this))
    }
}
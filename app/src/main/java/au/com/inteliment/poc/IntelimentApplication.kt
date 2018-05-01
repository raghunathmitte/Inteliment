package au.com.inteliment.poc

import android.app.Application
import au.com.inteliment.poc.di.AppComponent
import au.com.inteliment.poc.di.AppModule
import au.com.inteliment.poc.di.DaggerAppComponent


class IntelimentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerComponents()
    }

    fun initDaggerComponents() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        var appComponent: AppComponent? = null
    }
}
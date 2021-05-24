package br.gov.gelsonstoduto.desafionexaas

import android.app.Application
import br.gov.gelsonstoduto.desafionexaas.di.AppComponent
import br.gov.gelsonstoduto.desafionexaas.di.AppModule
import br.gov.gelsonstoduto.desafionexaas.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}
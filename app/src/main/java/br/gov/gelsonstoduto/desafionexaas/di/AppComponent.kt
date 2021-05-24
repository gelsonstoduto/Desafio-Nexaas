package br.gov.gelsonstoduto.desafionexaas.di

import br.gov.gelsonstoduto.desafionexaas.vm.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel?)
}
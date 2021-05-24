package br.gov.gelsonstoduto.desafionexaas.di

import android.app.Application
import br.gov.gelsonstoduto.desafionexaas.App
import br.gov.gelsonstoduto.desafionexaas.R
import br.gov.gelsonstoduto.desafionexaas.repository.remote.service.ProductsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val connectTimeOut = application.resources.getInteger(R.integer.connect_timeout).toLong()
        val writeTimeout = application.resources.getInteger(R.integer.write_timeout).toLong()
        val readTimeout = application.resources.getInteger(R.integer.read_timeout).toLong()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(DefaultParametersInterceptor(application as App))
            .build()

        return Retrofit.Builder()
            .baseUrl(ProductsService.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
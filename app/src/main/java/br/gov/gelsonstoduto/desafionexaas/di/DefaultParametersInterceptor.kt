package br.gov.gelsonstoduto.desafionexaas.di

import br.gov.gelsonstoduto.desafionexaas.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class DefaultParametersInterceptor(private val application: App) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
    }

}
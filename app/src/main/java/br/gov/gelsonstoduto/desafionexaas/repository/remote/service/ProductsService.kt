package br.gov.gelsonstoduto.desafionexaas.repository.remote.service

import br.gov.gelsonstoduto.desafionexaas.repository.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductsService {
    @GET("data.json")
    fun getProducts(): Call<List<Product>>

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"
    }
}
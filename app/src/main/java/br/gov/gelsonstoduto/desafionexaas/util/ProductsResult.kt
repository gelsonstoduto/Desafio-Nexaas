package br.gov.gelsonstoduto.desafionexaas.util

import br.gov.gelsonstoduto.desafionexaas.repository.model.Product

sealed class ProductsResult<out R>

// By using Nothing as T, Loading is a subtype of all NetworkResult<T>
object Loading: ProductsResult<Nothing>()

// Successful results are stored in data
//data class Success<out T>(val data: T): ProductsResult<T>()
data class Success<out T>(val data: List<Product>?): ProductsResult<T>()

// By using Nothing as T, all NetworkError instances are a subtypes of all NetworkResults<T>
data class Error<out T>(val exception: Throwable): ProductsResult<T>()

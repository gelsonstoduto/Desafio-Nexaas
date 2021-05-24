@file:Suppress("UNCHECKED_CAST")

package br.gov.gelsonstoduto.desafionexaas.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.gov.gelsonstoduto.desafionexaas.App
import br.gov.gelsonstoduto.desafionexaas.repository.model.Product
import br.gov.gelsonstoduto.desafionexaas.repository.remote.service.ProductsService
import br.gov.gelsonstoduto.desafionexaas.ui.BaseViewModel
import br.gov.gelsonstoduto.desafionexaas.util.Error
import br.gov.gelsonstoduto.desafionexaas.util.ProductsResult
import br.gov.gelsonstoduto.desafionexaas.util.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val app: App = application as App
    var sucessApi = MutableLiveData<Boolean>()
    var listProducts: MutableList<Product> = ArrayList()
    @JvmField
    @Inject
    var retrofit: Retrofit? = null

    class Factory(private val application: App) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }

    }

    fun loadProductsAPI() {
        viewModelScope.launch {
            val productDataResponse = getDataAPI()

            if(productDataResponse is Success) {
                sucessApi.postValue(true)
                updateProducts(productDataResponse.data)
            }

            if(productDataResponse is Error) {
                sucessApi.postValue(false)
            }
        }
    }

    private suspend fun getDataAPI() = withContext(Dispatchers.IO) {
        var res: ProductsResult<List<Product>> = try {
            Success(
                retrofit!!.create(ProductsService::class.java).getProducts().execute().body()
            )

        } catch (e: Exception) {
            sucessApi.postValue(false)
            Error(e)
        }
        res
    }

    private fun updateProducts(productDataResponse: List<Product>?) {
        if (productDataResponse != null) {
            for (item in productDataResponse) {
                val product = Product()
                product.name = item.name
                product.quantity = item.quantity
                product.stock = item.stock
                product.image_url = item.image_url
                product.price = item.price!! /100
                product.tax = item.tax!! /100
                product.shipping = item.shipping!!/ 100
                product.description = item.description
                listProducts.add(product)
            }
        }
    }

    init {
        app.appComponent.inject(this)
    }
}
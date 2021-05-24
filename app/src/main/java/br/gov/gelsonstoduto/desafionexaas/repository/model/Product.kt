package br.gov.gelsonstoduto.desafionexaas.repository.model

import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("quantity")
    var quantity: Int? = null
    @SerializedName("stock")
    var stock: Int? = null
    @SerializedName("image_url")
    var image_url: String? = null
    @SerializedName("price")
    var price: Double? = null
    @SerializedName("tax")
    var tax: Double? = null
    @SerializedName("shipping")
    var shipping: Double? = null
    @SerializedName("description")
    var description: String? = null
}
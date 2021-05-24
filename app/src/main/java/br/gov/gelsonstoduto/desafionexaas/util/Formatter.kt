package br.gov.gelsonstoduto.desafionexaas.util

import java.text.NumberFormat

object Formatter {
    fun doubleToUS(value: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        return format.format(value).replace("R$", "$")
    }
}
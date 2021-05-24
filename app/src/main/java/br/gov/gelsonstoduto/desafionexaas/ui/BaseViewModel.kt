package br.gov.gelsonstoduto.desafionexaas.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(private val baseApp: Application) : AndroidViewModel(
    baseApp
) {
    var loading = MutableLiveData<Boolean>()

}
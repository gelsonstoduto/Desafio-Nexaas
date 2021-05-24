package br.gov.gelsonstoduto.desafionexaas.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.gov.gelsonstoduto.desafionexaas.App
import br.gov.gelsonstoduto.desafionexaas.vm.MainViewModel

abstract class BaseFragment : Fragment() {
    protected lateinit var app: App
    protected lateinit var activity: BaseActivity
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        app = getActivity()!!.application as App
        activity = getActivity() as BaseActivity
    }

    abstract fun getViewModel(): MainViewModel
}
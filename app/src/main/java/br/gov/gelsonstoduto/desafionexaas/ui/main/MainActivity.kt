package br.gov.gelsonstoduto.desafionexaas.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.lifecycle.ViewModelProviders
import br.gov.gelsonstoduto.desafionexaas.App
import br.gov.gelsonstoduto.desafionexaas.R
import br.gov.gelsonstoduto.desafionexaas.repository.model.Product
import br.gov.gelsonstoduto.desafionexaas.ui.BaseActivity
import br.gov.gelsonstoduto.desafionexaas.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getBaseVM(): MainViewModel {
        return ViewModelProviders
            .of(this, MainViewModel.Factory(application as App))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (viewModel as MainViewModel).loadProductsAPI()
        setFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    private fun setFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, HomeFragment.newInstance())
            .commit()
    }

    fun setFragmentDetail(
        itemView: Product,
        indice: Int
    ) {
        btActionCheckout.visibility = View.GONE
        btActionRemove.visibility = View.VISIBLE
        val bundle = Bundle()
        bundle.putInt("position", indice)
        bundle.putString("image_url", itemView.image_url)
        bundle.putString("name", itemView.name)
        bundle.putString("price", itemView.price!!.toString())
        bundle.putString("stock", itemView.stock!!.toString())
        bundle.putString("description", itemView.description)

        val fragmentDetail = DetalheFragment()
        fragmentDetail.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmentDetail)
            .commit()
    }

    override fun onBackPressed() {
        btActionCheckout.visibility = View.VISIBLE
        btActionRemove.visibility = View.GONE
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, HomeFragment.newInstance())
            .commit()
    }
}
package br.gov.gelsonstoduto.desafionexaas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.gov.gelsonstoduto.desafionexaas.R
import br.gov.gelsonstoduto.desafionexaas.adapter.ProductAdapter
import br.gov.gelsonstoduto.desafionexaas.dialogs.AppDialogGeneric
import br.gov.gelsonstoduto.desafionexaas.repository.model.Product
import br.gov.gelsonstoduto.desafionexaas.ui.BaseFragment
import br.gov.gelsonstoduto.desafionexaas.util.Formatter
import br.gov.gelsonstoduto.desafionexaas.vm.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : BaseFragment() {
    private lateinit var vm: MainViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var mLayoutManagerMyProducts: LinearLayoutManager
    private var totalProducts: Double = 0.0
    private var subTotalProducts: Double = 0.0
    private var totalShipping: Double = 0.0
    private var totalTax: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setViews(inflater.inflate(R.layout.fragment_home, container, false))
    }

    private fun setViews(v: View): View {
        v.pBarProduct.visibility = View.VISIBLE
        return v
    }

    private fun loadProducts() {
        rv_products.setHasFixedSize(true)
        mLayoutManagerMyProducts = object : LinearLayoutManager(context, VERTICAL, false) {}
        rv_products.layoutManager = mLayoutManagerMyProducts
        adapter = ProductAdapter(vm.listProducts, this)
        rv_products.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()
        setObservers()
    }

    private fun setObservers() {
        vm.sucessApi.observe(
            this,
            Observer { sucesso: Boolean ->
                if (sucesso) {
                    getTotals()
                    qtProducts.text = vm.listProducts.size.toString() + " " + getString(R.string.qt_items)
                    vlrTotalProduct.text = Formatter.doubleToUS(totalProducts)
                    vlrSubTotalProduct.text = Formatter.doubleToUS(subTotalProducts)
                    vlrShipping.text = Formatter.doubleToUS(totalShipping)
                    vlrTax.text = Formatter.doubleToUS(totalTax)

                    loadProducts()
                    pBarProduct.visibility = View.INVISIBLE
                } else {
                    val appDialogGeneric = AppDialogGeneric(
                        getActivity()!!.application.getString(R.string.message_ops),
                        getActivity()!!.application.getString(R.string.message_api_error)
                    )
                    appDialogGeneric.show(getActivity()!!.supportFragmentManager, "genericApp")
                }
            }
        )
    }

    private fun getTotals() {
        for (produto in vm.listProducts) {
            totalProducts += (produto.price!! + produto.shipping!! + produto.tax!!)
            subTotalProducts += produto.price!!
            totalShipping += produto.shipping!!
            totalTax += produto.tax!!
        }
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders
            .of(activity, MainViewModel.Factory(app))
            .get(MainViewModel::class.java)
    }

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}
package br.gov.gelsonstoduto.desafionexaas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.gov.gelsonstoduto.desafionexaas.R
import br.gov.gelsonstoduto.desafionexaas.ui.BaseFragment
import br.gov.gelsonstoduto.desafionexaas.util.Formatter
import br.gov.gelsonstoduto.desafionexaas.vm.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detalhe.*


class DetalheFragment : BaseFragment(), IOonBackPressedInterface {
    private var args: Bundle? = Bundle()
    private lateinit var vm: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setViews(inflater.inflate(R.layout.fragment_detalhe, container, false))
    }

    private fun setViews(v: View): View {
        args = this.arguments
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()
        setItemProduct()
        setListener()
    }

    private fun setItemProduct() {
        name.text = args!!.getString("name")
        price.text = Formatter.doubleToUS(args!!.getString("price")!!.toDouble())
        stock.text = if (args!!.getString("stock")=="1") context!!.getString(R.string.one_in_stock)
                     else context!!.getString(R.string.in_stock)

        Glide.with(this)
            .load(args!!.getString("image_url"))
            .into(imgProduct)
        description.text = args!!.getString("description") + args!!.getString("description")
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders
            .of(activity, MainViewModel.Factory(app))
            .get(MainViewModel::class.java)
    }

    private fun setListener() {
        topAppBar.setOnClickListener {
            getActivity()!!.onBackPressed()
        }

        activity.btActionRemove.setOnClickListener {
            if (vm.listProducts.size > 0) {
                vm.listProducts.removeAt(args!!.get("position") as Int)
                activity.btActionCheckout.visibility = View.VISIBLE
                activity.btActionRemove.visibility = View.GONE
                getActivity()!!.onBackPressed()
            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return DetalheFragment()
        }
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}


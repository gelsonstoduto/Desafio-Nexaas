package br.gov.gelsonstoduto.desafionexaas.adapter

import android.animation.LayoutTransition
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.gov.gelsonstoduto.desafionexaas.R
import br.gov.gelsonstoduto.desafionexaas.repository.model.Product
import br.gov.gelsonstoduto.desafionexaas.ui.main.HomeFragment
import br.gov.gelsonstoduto.desafionexaas.ui.main.MainActivity
import br.gov.gelsonstoduto.desafionexaas.util.Formatter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lista_my_products.view.*


class ProductAdapter(l: List<Product>, c: HomeFragment) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val mProductList: List<Product> = l
    private val context: Context? = c.context

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_my_products, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = mProductList[position]

        context?.let {
            Glide.with(it)
                .load(item.image_url)
                .into(holder.itemView.imgProduct)
        }

        holder.itemView.txtTitleProduct.text = item.name
        holder.itemView.txtSubTitleProduct.text = if (item.stock == 1) context!!.getString(R.string.one_in_stock)
                                                  else context?.getString(R.string.in_stock)
        holder.itemView.txtPriceProduct.text = Formatter.doubleToUS(item.price!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.itemView.cardViewProducts.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            }
        }

        holder.itemView.cardViewProducts.setOnClickListener {
            loadItemProduct(item, it, position)
        }
    }

    private fun loadItemProduct(
        item: Product,
        it: View,
        position: Int
    ) {
        var itemProduct: Product = Product()
        itemProduct.image_url = item.image_url
        itemProduct.name = item.name
        itemProduct.price = item.price
        itemProduct.stock = item.stock
        itemProduct.description = item.description

        val context = it.context as MainActivity
        context.setFragmentDetail(itemProduct, position)
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

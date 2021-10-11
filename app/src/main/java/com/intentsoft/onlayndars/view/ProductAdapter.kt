package com.intentsoft.onlayndars.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intentsoft.onlayndars.R
import com.intentsoft.onlayndars.model.ProductModel
import com.intentsoft.onlayndars.screen.cart.productdetail.ProductdetailActivity
import com.intentsoft.onlayndars.utils.Constants
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(val items : List<ProductModel>):RecyclerView.Adapter<ProductAdapter.ItemHolder>() {
    class ItemHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load("http://osonsavdo.sd-group.uz/images/${item.image}").into(holder.itemView.imgProduct)
        holder.itemView.tvName.text = item.name
        holder.itemView.tvPrice.text = item.price


        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,ProductdetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
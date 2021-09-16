package com.example.onlayndars.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlayndars.R
import com.example.onlayndars.model.CategoryModel
import kotlinx.android.synthetic.main.category_item_layout.view.*


interface CategoryAdapterCallBack{
    fun onClickItem(item:CategoryModel)
}
class CategoryAdapter(val items: List<CategoryModel>,val callBack: CategoryAdapterCallBack): RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    class ItemHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item =items[position]
        holder.itemView.tvTitle.text = item.title

        holder.itemView.setOnClickListener {
            items.forEach {
                it.checked = false

            }
            item.checked = true
            notifyDataSetChanged()

            callBack.onClickItem(item)
        }

        if (item.checked){
            holder.itemView.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
            holder.itemView.tvTitle.setTextColor(Color.WHITE)
        }else{
            holder.itemView.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.Gray))
            holder.itemView.tvTitle.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
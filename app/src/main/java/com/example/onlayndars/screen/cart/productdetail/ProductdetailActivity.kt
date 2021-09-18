package com.example.onlayndars.screen.cart.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.onlayndars.R
import com.example.onlayndars.model.ProductModel
import com.example.onlayndars.utils.Constants
import com.example.onlayndars.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_productdetail.*
import kotlinx.android.synthetic.main.activity_productdetail.tvTitle

lateinit var item: ProductModel
class ProductdetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetail)

         cardViewFavorite.setOnClickListener {
             PrefUtils.setFavorite(item)

             if (PrefUtils.checkFavorite(item)){
                 imageFavorite.setImageResource(R.drawable.ic_heart)
             }else{
                 imageFavorite.setImageResource(R.drawable.heart)
             }
         }

        cardViewBack.setOnClickListener {
            finish()
        }
        item = intent.getSerializableExtra(Constants.EXTRA_DATA) as ProductModel

        tvTitle.text = item.name
        tvProductName.text = item.name
        tvProductPrice.text = item.price

        if (PrefUtils.checkFavorite(item)){
            imageFavorite.setImageResource(R.drawable.ic_heart)
        }else{
            imageFavorite.setImageResource(R.drawable.heart)
        }

        Glide.with(this).load(Constants.HOST_IMAGE + item.image).into(imgProduct)
    }
}
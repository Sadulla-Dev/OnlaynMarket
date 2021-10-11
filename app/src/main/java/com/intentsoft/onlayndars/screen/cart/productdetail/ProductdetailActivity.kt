package com.intentsoft.onlayndars.screen.cart.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.intentsoft.onlayndars.R
import com.intentsoft.onlayndars.model.ProductModel
import com.intentsoft.onlayndars.utils.Constants
import com.intentsoft.onlayndars.utils.PrefUtils
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


        if (PrefUtils.getCartCount(item) > 0){
            btnAdd2Cart.visibility = View.GONE
        }
        if (PrefUtils.checkFavorite(item)){
            imageFavorite.setImageResource(R.drawable.ic_heart)
        }else{
            imageFavorite.setImageResource(R.drawable.heart)
        }

        Glide.with(this).load(Constants.HOST_IMAGE + item.image).into(imgProduct)


        btnAdd2Cart.setOnClickListener {
            item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Mahsulot savatga qo'shildi!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
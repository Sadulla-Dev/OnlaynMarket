package com.example.onlayndars.api

import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("get_offers")
    fun getOffers(): Call<BaseResposne<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Call<BaseResposne<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProduct(): Call<BaseResposne<List<ProductModel>>>
}
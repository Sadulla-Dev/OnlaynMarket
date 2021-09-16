package com.example.onlayndars.api

import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.model.ProductModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("get_offers")
    fun getOffers(): Observable<BaseResposne<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResposne<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProduct(): Observable<BaseResposne<List<ProductModel>>>
    @GET("get_products/{category_id}")
    fun getCategoryProduct(@Path("category_id") categoryId: Int): Observable<BaseResposne<List<ProductModel>>>
}
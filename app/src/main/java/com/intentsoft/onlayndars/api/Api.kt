package com.intentsoft.onlayndars.api

import com.intentsoft.onlayndars.model.BaseResposne
import com.intentsoft.onlayndars.model.CategoryModel
import com.intentsoft.onlayndars.model.OfferModel
import com.intentsoft.onlayndars.model.ProductModel
import com.intentsoft.onlayndars.model.request.GetProductsByIdsRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("get_products_by_ids")
    fun getProductsByIds(@Body request: GetProductsByIdsRequest): Observable<BaseResposne<List<ProductModel>>>
}
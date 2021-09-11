package com.example.onlayndars.api

import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.OfferModel
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("get_offers")
    fun getOffers(): Call<BaseResposne<List<OfferModel>>>
}
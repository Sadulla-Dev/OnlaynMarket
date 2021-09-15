package com.example.onlayndars.screen.cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlayndars.api.Api
import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.model.ProductModel
import com.example.onlayndars.view.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://osonsavdo.sd-group.uz/api/")
        .build()

    val api = retrofit.create(Api::class.java)

    val offerData = MutableLiveData<List<OfferModel>>()
    val error = MutableLiveData<String>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val productsData = MutableLiveData<List<ProductModel>>()

    fun getOffers(){
        api.getOffers().enqueue(object : Callback<BaseResposne<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResposne<List<OfferModel>>>,
                response: Response<BaseResposne<List<OfferModel>>>
            ) {

                if (response.isSuccessful && response.body()!!.success){
                    offerData.value = response.body()!!.data
                }else{
                    error.value = response.body()?.message ?: response.message()
                }
            }

            override fun onFailure(call: Call<BaseResposne<List<OfferModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })

    }


    fun getCattegories(){
        api.getCategories().enqueue(object : Callback<BaseResposne<List<CategoryModel>>>{
            override fun onResponse(
                call: Call<BaseResposne<List<CategoryModel>>>,
                response: Response<BaseResposne<List<CategoryModel>>>) {

                if (response.isSuccessful && response.body()!!.success){
                    categoriesData.value = response.body()!!.data
                }else{
                    error.value = response.body()?.message ?: response.message()
                }
            }

            override fun onFailure(call: Call<BaseResposne<List<CategoryModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    fun getTopProducts(){
        api.getTopProduct().enqueue(object : Callback<BaseResposne<List<ProductModel>>>{
            override fun onResponse(
                call: Call<BaseResposne<List<ProductModel>>>,
                response: Response<BaseResposne<List<ProductModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                    productsData.value = response.body()!!.data
                }else{
                    error.value = response.body()?.message ?: response.message()
                }
            }

            override fun onFailure(call: Call<BaseResposne<List<ProductModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }


}
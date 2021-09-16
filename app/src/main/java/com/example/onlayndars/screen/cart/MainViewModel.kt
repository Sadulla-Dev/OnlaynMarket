package com.example.onlayndars.screen.cart

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlayndars.api.Api
import com.example.onlayndars.api.NetvorkManager
import com.example.onlayndars.api.repository.ShopRepository
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


    val progress = MutableLiveData<Boolean>()
    val repository = ShopRepository()
    val offerData = MutableLiveData<List<OfferModel>>()
    val error = MutableLiveData<String>()
    val categoriesData = MutableLiveData<List<CategoryModel>>()
    val productsData = MutableLiveData<List<ProductModel>>()

    fun getOffers() {
         repository.getOffers(error,progress,offerData)
    }
    fun getCattegories() {
        repository.getCattegories(error,categoriesData)
    }
    fun getTopProducts() {
        repository.getTopProducts(error,productsData)
    }
    fun getProductsByCategory(id: Int) {
        repository.getProductsByCategory(id, error, productsData)
    }

}
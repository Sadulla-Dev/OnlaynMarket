package com.example.onlayndars.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlayndars.api.NetvorkManager
import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.model.ProductModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepository {

    val compasiteDisposable = CompositeDisposable()
     fun getOffers(error: MutableLiveData<String>, progress: MutableLiveData<Boolean>, success: MutableLiveData<List<OfferModel>>){
         progress.value = true
         compasiteDisposable.add(NetvorkManager
                 .getApiService().getOffers()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableObserver<BaseResposne<List<OfferModel>>>(){
                 override fun onNext(t: BaseResposne<List<OfferModel>>) {
                     progress.value = false
                     if (t.success) {
                         success.value = t.data
                     }else{
                         error.value = t.message
                     }
                 }

                 override fun onError(e: Throwable) {
                     progress.value = true
                     error.value = e.localizedMessage
                 }

                 override fun onComplete() {
                 }
             })
         )


     }


     fun getCattegories(error: MutableLiveData<String>,success: MutableLiveData<List<CategoryModel>>){
         compasiteDisposable.add(NetvorkManager
             .getApiService().getCategories()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableObserver<BaseResposne<List<CategoryModel>>>(){
                 override fun onNext(t: BaseResposne<List<CategoryModel>>) {
                     if (t.success) {
                         success.value = t.data
                     }else{
                         error.value = t.message
                     }
                 }

                 override fun onError(e: Throwable) {
                     error.value = e.localizedMessage
                 }

                 override fun onComplete() {
                 }
             })
         )
     }

     fun getTopProducts(error: MutableLiveData<String>,success: MutableLiveData<List<ProductModel>>){
         compasiteDisposable.add(NetvorkManager
             .getApiService().getTopProduct()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableObserver<BaseResposne<List<ProductModel>>>(){
                 override fun onNext(t: BaseResposne<List<ProductModel>>) {
                     if (t.success) {
                         success.value = t.data
                     }else{
                         error.value = t.message
                     }
                 }

                 override fun onError(e: Throwable) {
                     error.value = e.localizedMessage
                 }

                 override fun onComplete() {
                 }
             })
         )
     }
    fun getProductsByCategory(id: Int,error: MutableLiveData<String>,success: MutableLiveData<List<ProductModel>>){
         compasiteDisposable.add(NetvorkManager
             .getApiService().getCategoryProduct(id)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableObserver<BaseResposne<List<ProductModel>>>(){
                 override fun onNext(t: BaseResposne<List<ProductModel>>) {
                     if (t.success) {
                         success.value = t.data
                     }else{
                         error.value = t.message
                     }
                 }

                 override fun onError(e: Throwable) {
                     error.value = e.localizedMessage
                 }

                 override fun onComplete() {
                 }
             })
         )
     }
 }
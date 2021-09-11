package com.example.onlayndars.screen.cart.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlayndars.R
import com.example.onlayndars.api.Api
import com.example.onlayndars.model.BaseResposne
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.view.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    var offers: List<OfferModel> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        recyclerviewCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL, false)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://osonsavdo.sd-group.uz/api/")
            .build()

        val api = retrofit.create(Api::class.java)

        api.getOffers().enqueue(object : Callback<BaseResposne<List<OfferModel>>>{
            override fun onResponse(
                call: Call<BaseResposne<List<OfferModel>>>,
                response: Response<BaseResposne<List<OfferModel>>>) {

                if (response.isSuccessful && response.body()!!.success){
                    offers = response.body()!!.data
                    carouselView.setImageListener { position, imageView ->
                        Glide.with(imageView).load("http://osonsavdo.sd-group.uz/images/${offers[position].image}").into(imageView)
                    }

                    carouselView.pageCount = offers.count()
                }else{
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<BaseResposne<List<OfferModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })


        api.getCategories().enqueue(object : Callback<BaseResposne<List<CategoryModel>>>{
            override fun onResponse(
                call: Call<BaseResposne<List<CategoryModel>>>,
                response: Response<BaseResposne<List<CategoryModel>>>) {

                if (response.isSuccessful && response.body()!!.success){
                    recyclerviewCategory.adapter = CategoryAdapter(response.body()?.data ?: emptyList())
                }else{
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<BaseResposne<List<CategoryModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })


    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}
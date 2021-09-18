package com.example.onlayndars.screen.cart.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlayndars.R
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.OfferModel
import com.example.onlayndars.screen.cart.MainViewModel
import com.example.onlayndars.view.CategoryAdapter
import com.example.onlayndars.view.CategoryAdapterCallBack
import com.example.onlayndars.view.ProductAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    var offers: List<OfferModel> = emptyList()
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        swipes.setOnRefreshListener {
            swipes.isRefreshing = true
            loadData()
        }



        recyclerviewCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL, false)
        recyclerviewProduct.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)



        viewModel.progress.observe(requireActivity(), Observer {
            swipes.isRefreshing = it
        })

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.offerData.observe(requireActivity(), Observer {
            carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load("http://osonsavdo.sd-group.uz/images/${it[position].image}").into(imageView)
            }
            carouselView.pageCount = it.count()
        })

        viewModel.categoriesData.observe( requireActivity(), Observer {
           recyclerviewCategory.adapter = CategoryAdapter(it,object: CategoryAdapterCallBack{
               override fun onClickItem(item: CategoryModel) {
                   viewModel.getProductsByCategory(item.id)
               }
           })
        })

        viewModel.productsData.observe(requireActivity(), Observer {
            recyclerviewProduct.adapter = ProductAdapter(it)
        })

        loadData()

    }

    fun loadData(){
        viewModel.getOffers()
        viewModel.getCattegories()
        viewModel.getTopProducts()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}
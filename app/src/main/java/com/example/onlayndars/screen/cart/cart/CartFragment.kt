package com.example.onlayndars.screen.cart.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlayndars.R
import com.example.onlayndars.model.ProductModel
import com.example.onlayndars.screen.cart.MainViewModel
import com.example.onlayndars.screen.cart.makeOrder.MakeOrderActivity
import com.example.onlayndars.utils.Constants
import com.example.onlayndars.utils.PrefUtils
import com.example.onlayndars.view.CartAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import java.io.Serializable


class CartFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.progress.observe(this, Observer {
            swipeCart.isRefreshing = it
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.productsData.observe(this, Observer {
            recyclerCart.adapter = CartAdapter(it)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCart.layoutManager = LinearLayoutManager(requireActivity())

        swipeCart.setOnRefreshListener {
            loadData()
        }
        loadData()



        btnMakeOrder.setOnClickListener{
            val intent = Intent(requireActivity(),MakeOrderActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA,(viewModel.productsData.value ?: emptyList<ProductModel>()) as Serializable)
            startActivity(intent)
        }
    }



    fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.product_id })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}
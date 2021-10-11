package com.intentsoft.onlayndars


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.intentsoft.onlayndars.screen.cart.MainViewModel
import com.intentsoft.onlayndars.screen.cart.cart.CartFragment
import com.intentsoft.onlayndars.screen.cart.changeLanguage.ChangeLanguageFragment
import com.intentsoft.onlayndars.screen.cart.favourite.FavouriteFragment
import com.intentsoft.onlayndars.screen.cart.home.HomeFragment
import com.intentsoft.onlayndars.screen.cart.profile.ProfileFragment
import com.intentsoft.onlayndars.utils.LocaleManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment.newInstance()
    val favoriteFragment = FavouriteFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val profileFragment = ProfileFragment.newInstance()
    var activeFragment: Fragment = homeFragment

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainViewModel()

        viewModel.productsData.observe(this, Observer {
            viewModel.insertAllProducts2DB(it)

        })

        viewModel.categoriesData.observe(this, Observer {
            viewModel.insertAllCategories2DB(it)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Qayta urinib ko'ring" , Toast.LENGTH_SHORT).show()
        })

        supportFragmentManager.beginTransaction().add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer, favoriteFragment, favoriteFragment.tag).hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer, cartFragment, cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer, profileFragment, profileFragment.tag).hide(profileFragment).commit()
        supportFragmentManager.beginTransaction().show(activeFragment).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.homeFragment){
                supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = homeFragment
            }else if (it.itemId == R.id.cartFragment){
                supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit()
                activeFragment = cartFragment
            }else if (it.itemId == R.id.favouriteFragment){
                supportFragmentManager.beginTransaction().hide(activeFragment).show(favoriteFragment).commit()
                activeFragment = favoriteFragment
            }else if (it.itemId == R.id.profileFragment){
                supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                activeFragment = profileFragment
                Toast.makeText(this, "Vaqtinchalik bu qism faol emas!", Toast.LENGTH_SHORT).show()
            }
            return@setOnNavigationItemSelectedListener true
        }
        btnMenu.setOnClickListener {
            val fragment = ChangeLanguageFragment.newInstance()
            fragment.show(supportFragmentManager, fragment.tag)
        }

        loadData()
    }

    fun loadData() {
        viewModel.getTopProducts()
        viewModel.getCattegories()
        viewModel.getAllDBCategories()
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}
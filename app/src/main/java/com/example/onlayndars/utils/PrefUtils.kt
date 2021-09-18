package com.example.onlayndars.utils

import com.example.onlayndars.model.ProductModel
import com.orhanobut.hawk.Hawk

object PrefUtils {
    const val PREF_FAVORITES = "pref_favorites"

    fun setFavorite(item: ProductModel) {
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())

        if (items.filter { it == item.id }.firstOrNull() != null) {
            items.remove(item.id)
        }else{
            items.add(item.id)
        }

        Hawk.put(PREF_FAVORITES,items)

    }

    fun getFavoritesList(): ArrayList<Int>{
        return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    }

    fun checkFavorite(item: ProductModel): Boolean{
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        return items.filter { it == item.id }.firstOrNull() != null
    }
}
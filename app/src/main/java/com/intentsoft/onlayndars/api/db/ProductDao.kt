package com.intentsoft.onlayndars.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intentsoft.onlayndars.model.ProductModel


@Dao
interface ProductDao {
    @Query("DELETE from products")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ProductModel>)

    @Query("select * from products")
    fun getAllProducts(): List<ProductModel>
}
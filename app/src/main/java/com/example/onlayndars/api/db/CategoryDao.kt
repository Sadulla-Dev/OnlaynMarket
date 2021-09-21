package com.example.onlayndars.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlayndars.model.CategoryModel


@Dao
interface CategoryDao {
    @Query("DELETE from categories")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insatAll(items: List<CategoryModel>)

    @Query("select * from categories")
    fun getAllCategories(): List<CategoryModel>
}
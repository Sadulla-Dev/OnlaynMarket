package com.example.onlayndars.api.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlayndars.model.CategoryModel
import com.example.onlayndars.model.ProductModel
import java.security.AccessControlContext


@Database(entities = [CategoryModel::class,ProductModel::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryDao(): CategoryDao
    companion object{
        var INSTANCE: AppDatabase? = null
        fun initDatabase(context: Context){
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java , "online_shop_db").build()
                }
            }
        }


        fun getDataBase(): AppDatabase{
            return  INSTANCE!!
        }
    }
}
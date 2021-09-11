package com.example.onlayndars.model

data class CategoryModel(
    val id: Int,
    val title: String,
    val icon: String,
    var checked: Boolean = false
)
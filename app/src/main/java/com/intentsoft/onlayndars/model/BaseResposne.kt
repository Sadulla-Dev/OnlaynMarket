package com.intentsoft.onlayndars.model

data class BaseResposne<T>(
    val success: Boolean,
    val data: T,
    val message: String,
    val error_code: Int
)
package com.example.calenderapplicationfrnd.utils

import com.google.gson.JsonObject
import com.google.gson.JsonParser

data class ErrorBody(val message: String, val code: Int = -1, val rawMessage: String)

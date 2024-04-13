package com.bigs.short_term_forecast.clients.response

data class Item (
    val baseDate : String?,
    val baseTime : String?,
    val category : String?,
    val nx : Double?,
    val ny : Double?,
    val fcstValue: String?,
    val fcstDate: String?,
    val fcstTime: String?,
    )
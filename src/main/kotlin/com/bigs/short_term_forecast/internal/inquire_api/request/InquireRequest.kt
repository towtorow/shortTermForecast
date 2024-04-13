package com.bigs.short_term_forecast.internal.inquire_api.request

data class InquireRequest (
    val category : String,
    val fcstDate: String,
    val fcstTime: String
)
package com.bigs.short_term_forecast.domain

import jakarta.persistence.*

@Entity
class ShortTermForecast(
    private var baseDate: String?,
    private var baseTime: String?,
    private var category: String?,
    private var nx: Double?,
    private var ny: Double?,
    private var fcstValue: String?,
    private var fcstDate: String?,
    private var fcstTime: String?,
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id : Long = -1

    val getId: Long
        get() = id

    val getBaseDate: String?
        get() = baseDate

    val getBaseTime: String?
        get() = baseTime

    val getCategory: String?
        get() = category

    val getNx: Double?
        get() = nx

    val getNy: Double?
        get() = ny

    val getFcstValue: String?
        get() = fcstValue

    val getFcstDate: String?
        get() = fcstDate

    val getFcstTime: String?
        get() = fcstTime

    override fun toString(): String {
        return "ShortTermForecast(baseDate=$baseDate, baseTime=$baseTime, category=$category, nx=$nx, ny=$ny, fcstValue=$fcstValue, fcstDate=$fcstDate, fcstTime=$fcstTime, id=$id)"
    }

}
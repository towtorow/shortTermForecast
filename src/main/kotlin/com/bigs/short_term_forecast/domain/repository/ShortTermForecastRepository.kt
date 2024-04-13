package com.bigs.short_term_forecast.domain.repository

import com.bigs.short_term_forecast.domain.ShortTermForecast
import org.springframework.data.jpa.repository.JpaRepository

interface ShortTermForecastRepository : JpaRepository<ShortTermForecast, Long> {
    // BaseDate와 BaseTime이 가장 최근인 데이터를 조회함
    fun findFirstByCategoryAndFcstDateAndFcstTimeOrderByBaseDateDescBaseTimeDesc(category : String, fcstDate: String, fcstTime: String): ShortTermForecast?
    // 해당 BaseDate와 BaseTime이 일치하는 데이터가 있는지 확인함
    // 만약 데이터가 있으면 이미 그 날은 Sync가 완료되었다고 간주하고 아무 작업도 하지 않음
    fun findFirstByBaseDateAndBaseTime(baseDate: String, baseTime: String): ShortTermForecast?
}
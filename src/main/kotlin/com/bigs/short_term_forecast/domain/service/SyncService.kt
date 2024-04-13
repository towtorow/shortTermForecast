package com.bigs.short_term_forecast.domain.service

import com.bigs.short_term_forecast.domain.ShortTermForecast

interface SyncService {

    fun saveIfNotExist(shortTermForecasts: List<ShortTermForecast>)

}
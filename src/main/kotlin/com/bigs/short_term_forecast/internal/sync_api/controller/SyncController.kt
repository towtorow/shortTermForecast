package com.bigs.short_term_forecast.internal.sync_api.controller

import com.bigs.short_term_forecast.domain.ShortTermForecast
import com.bigs.short_term_forecast.clients.short_term_forecast_client.ForecastDataCaller
import com.bigs.short_term_forecast.domain.service.SyncService
import com.bigs.short_term_forecast.domain.service.impl.SyncServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sync_api/v1")
class SyncController {

    private val logger: Logger = LoggerFactory.getLogger(SyncController::class.java)

    private lateinit var syncService: SyncService

    @Autowired
    fun setSyncServiceImpl(syncService: SyncServiceImpl) {
        this.syncService = syncService
    }

    @PostMapping("sync")
    fun sync(): ResponseEntity<Any> {
        // 공공 데이터 포탈 데이터 조회
        val items = ForecastDataCaller().call()
        val shortTermForecasts = ArrayList<ShortTermForecast>()
        if (items != null) {
            for (item in items){
                shortTermForecasts.add(ShortTermForecast(item.baseDate, item.baseTime, item.category, item.nx, item.ny,item.fcstValue, item.fcstDate, item.fcstTime ))
            }
        }
        syncService.saveIfNotExist(shortTermForecasts)
        return ResponseEntity.ok(mapOf(
            "message" to "Sync Successful",
        ))
    }

}
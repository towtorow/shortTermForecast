package com.bigs.short_term_forecast.domain.service.impl
import com.bigs.short_term_forecast.domain.ShortTermForecast
import com.bigs.short_term_forecast.domain.repository.ShortTermForecastRepository
import com.bigs.short_term_forecast.domain.service.SyncService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SyncServiceImpl : SyncService {

    private lateinit var shortTermForecastRepository: ShortTermForecastRepository

    @Autowired
    fun setSyncRepository(shortTermForecastRepository: ShortTermForecastRepository) {
        this.shortTermForecastRepository = shortTermForecastRepository
    }
    // baseDate와 baseTime이 동일한 데이터가 없는 경우 DB에 데이터 적재
    override fun saveIfNotExist(shortTermForecasts: List<ShortTermForecast>) {
        val shortTermForecast = shortTermForecasts.getOrNull(0) ?: return
        val baseDate = shortTermForecast.getBaseDate ?: return
        val baseTime = shortTermForecast.getBaseTime ?: return
        val existed = shortTermForecastRepository.findFirstByBaseDateAndBaseTime(baseDate, baseTime)
        if (existed != null && existed.getId != 0L) {
            return
        } else {
            shortTermForecastRepository.saveAll(shortTermForecasts)
        }
    }
}
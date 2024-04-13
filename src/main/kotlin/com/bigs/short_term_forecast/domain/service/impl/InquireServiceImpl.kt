package com.bigs.short_term_forecast.domain.service.impl

import com.bigs.short_term_forecast.domain.repository.ShortTermForecastRepository
import com.bigs.short_term_forecast.internal.inquire_api.request.InquireRequest
import com.bigs.short_term_forecast.internal.inquire_api.response.InquireResponse
import com.bigs.short_term_forecast.domain.service.InquireService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.rmi.NoSuchObjectException

@Service
class InquireServiceImpl: InquireService {

    private lateinit var shortTermForecastRepository: ShortTermForecastRepository

    @Autowired
    fun setSyncRepository(shortTermForecastRepository: ShortTermForecastRepository) {
        this.shortTermForecastRepository = shortTermForecastRepository
    }

    override fun findByInquireRequest(inquireRequest: InquireRequest) : InquireResponse {
        val shortTermForecast =
            shortTermForecastRepository
            .findFirstByCategoryAndFcstDateAndFcstTimeOrderByBaseDateDescBaseTimeDesc(
            inquireRequest.category,
            inquireRequest.fcstDate,
            inquireRequest.fcstTime
            )
        // 조건에 맞는 데이터가 없는 경우 NoSuchObjectException을 발생시킴
        // Advice에서 해당 NoSuchObjectException을 잡아서 204 응답을 보냄
        if(shortTermForecast == null || shortTermForecast.getId == 0L){
            throw NoSuchObjectException("Inquired data not exist")
        }
        return InquireResponse(shortTermForecast.getFcstValue)
    }

}
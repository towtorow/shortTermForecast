package com.bigs.short_term_forecast.domain.service

import com.bigs.short_term_forecast.internal.inquire_api.request.InquireRequest
import com.bigs.short_term_forecast.internal.inquire_api.response.InquireResponse

interface InquireService {

    fun findByInquireRequest(inquireRequest: InquireRequest) : InquireResponse

}
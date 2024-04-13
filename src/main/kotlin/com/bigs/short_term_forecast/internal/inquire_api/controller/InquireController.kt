package com.bigs.short_term_forecast.internal.inquire_api.controller

import com.bigs.short_term_forecast.internal.inquire_api.request.InquireRequest
import com.bigs.short_term_forecast.internal.inquire_api.response.InquireResponse
import com.bigs.short_term_forecast.domain.service.InquireService
import com.bigs.short_term_forecast.domain.service.impl.InquireServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inquire_api/v1")
class InquireController {

    private val logger: Logger = LoggerFactory.getLogger(InquireController::class.java)

    private lateinit var inquireService: InquireService

    @Autowired
    fun setInquireServiceImpl(inquireService: InquireServiceImpl) {
        this.inquireService = inquireService
    }

    @GetMapping("inquire")
    fun inquire(@RequestParam category : String,
                @RequestParam fcstDate: String,
                @RequestParam fcstTime: String
    ): ResponseEntity<InquireResponse> {
        val inquireRequest = InquireRequest(category, fcstDate, fcstTime)
        return ResponseEntity.ok(inquireService.findByInquireRequest(inquireRequest))
    }

}
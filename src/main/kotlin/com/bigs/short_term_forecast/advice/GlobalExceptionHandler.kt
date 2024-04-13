package com.bigs.short_term_forecast.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.rmi.NoSuchObjectException

@ControllerAdvice
class GlobalExceptionHandler {

    // 단기예보를 조회했을때 데이터가 없으면 204 응답 정의
    @ExceptionHandler(value = [NoSuchObjectException::class])
    fun handleNoSuchObjectException(e: NoSuchObjectException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    // Exception이 발생했을때 응답 정의
    @ExceptionHandler(value = [Exception::class])
    fun handleGeneralException(e: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf(
            "message" to e.message,
        ))
    }

}
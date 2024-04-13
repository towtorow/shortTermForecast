package com.bigs.short_term_forecast.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = [
    "com.bigs.short_term_forecast.internal.inquire_api",
    "com.bigs.short_term_forecast.advice",
    "com.bigs.short_term_forecast.domain.service"])
@EnableJpaRepositories(basePackages = ["com.bigs.short_term_forecast.domain.repository"])
@EntityScan(basePackages = ["com.bigs.short_term_forecast.domain"])
class InquireApplication
// DB에서 데이터를 조회하는 App
fun main(args: Array<String>) {
    runApplication<InquireApplication>(*args)
}

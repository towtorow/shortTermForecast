package com.bigs.short_term_forecast.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = [
    "com.bigs.short_term_forecast.internal.sync_api",
    "com.bigs.short_term_forecast.advice",
    "com.bigs.short_term_forecast.domain.service"])
@EnableJpaRepositories(basePackages = ["com.bigs.short_term_forecast.domain.repository"])
@EntityScan(basePackages = ["com.bigs.short_term_forecast.domain"])
class SyncApplication
// 공공데이터 포털에서 데이터를 가져와 DB에 적재하는 App
fun main(args: Array<String>) {
    runApplication<SyncApplication>(*args)
}

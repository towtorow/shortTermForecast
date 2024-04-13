package com.bigs.short_term_forecast.clients.short_term_forecast_client

import com.bigs.short_term_forecast.clients.response.Item
import com.bigs.short_term_forecast.clients.response.SyncResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
// 공공 데이터 포탈에서 데이터를 조회하는 객체
class ForecastDataCaller {

    private val logger: Logger = LoggerFactory.getLogger(ForecastDataCaller::class.java)

    fun call(): List<Item>? {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH")
        val formattedDateTime = formatter.format(now)
        val splitedFormattedDateTime = formattedDateTime.split(" ")
        val uriComponents = UriComponentsBuilder.fromUriString("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
            // 서비스 키를 넣어주셔야 동작합니다.
            .queryParam("ServiceKey", "서비스 키")
            .queryParam("pageNo", 1)
            // 데이터가 1000건 보다 작음
            .queryParam("numOfRows", 1000)
            .queryParam("dataType", "JSON")
            .queryParam("base_date", splitedFormattedDateTime[0])
            //.queryParam("base_time", splitedFormattedDateTime[1] + "00")
            // 공공데이터 포탈에 데이터 조회를 해본 결과 base_time을 새벽 5시로 설정해야 데이터가 들어옴
            // 또한 API 명세에도 새벽 5시에 업데이트 된다고 나와있음
            .queryParam("base_time", "0500")
            // 경기도 의정부시 문충로74 지역 위도와 경도
            .queryParam("nx", 37)
            .queryParam("ny", 127)
            .build()
        val url = uriComponents.toUriString()
        logger.info("call url: $url")
        val restTemplate = RestTemplate()
        val syncResponse : SyncResponse? = restTemplate.getForObject(url, SyncResponse::class.java)
        return syncResponse?.response?.body?.items?.item
    }
}
package com.yeoljeong.autovote.application.api

import com.yeoljeong.autovote.application.support.error.CoreException
import com.yeoljeong.autovote.application.support.error.ErrorType
import com.yeoljeong.autovote.application.support.response.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(javaClass.name)

    @ExceptionHandler(CoreException::class)
    fun handleCoreException(e: CoreException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("CoreException : {}", e.message, e)
            LogLevel.WARN -> log.warn("CoreException : {}", e.message, e)
            else -> log.info("CoreException : {}", e.message, e)
        }
        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }

}
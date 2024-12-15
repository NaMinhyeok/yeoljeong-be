package com.yeoljeong.autovote.application.support.error

class CoreException(
    val errorType: ErrorType,
    val data: Any? = null,
) : RuntimeException(errorType.message)
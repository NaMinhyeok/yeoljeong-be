package com.yeoljeong.autovote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutovoteApplication

fun main(args: Array<String>) {
    runApplication<AutovoteApplication>(*args)
}

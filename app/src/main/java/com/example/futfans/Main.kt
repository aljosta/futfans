package com.example.futfans

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val asyn1 = async {
            println("ALVARO-1")
        }

        val asycn1 = async {
            println("ALVARO-2")
        }

        delay(3000L)
        println("ALVARO-4")
    }

    println("ALVARO-5")
}

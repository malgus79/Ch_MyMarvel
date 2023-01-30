package com.example.mymarvel.core.common

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    const val BASE_URL = "https://gateway.marvel.com/"
    val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = ""
    const val PRIVATE_KEY = ""

    fun hash(): String {
        val input = "$timestamp$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    const val PAGE_INDEX = 1
    const val LIMIT = 20

    const val MARVEL_DATABASE = "marvel_database"
}
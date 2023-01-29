package com.example.mymarvel.core.common

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    const val BASE_URL = "https://gateway.marvel.com/"
    val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "8beaa13d97515c8626fd9b9c46e1618e"
    const val PRIVATE_KEY = "1f4032dc96a19e6e6f01b3f1635145cfc001f0e9"

    fun hash(): String {
        val input = "$timestamp$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
package com.crypto.network

import com.crypto.model.crypto.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiFactory {

    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=100&CMC_PRO_API_KEY=a28a27d1-e78c-4850-908d-e9dd98ffbfef


    @GET("v1/cryptocurrency/listings/latest?")

    suspend fun getData(
        @Header("CMC_PRO_API_KEY") apikey:String,
        @Query("limit") limit:String

    ):CryptoResponse



}
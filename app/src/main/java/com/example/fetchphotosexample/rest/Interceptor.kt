package com.example.fetchphotosexample.rest

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val orgRequest = chain.request()
        val reqBuilder = orgRequest.newBuilder()
        reqBuilder.addHeader("Content-Type", "application/json;charset=utf-8")
        return chain.proceed(reqBuilder.build())
    }
}
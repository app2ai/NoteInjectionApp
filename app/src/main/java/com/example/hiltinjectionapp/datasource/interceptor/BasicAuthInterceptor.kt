package com.example.hiltinjectionapp.datasource.interceptor

//import com.example.hiltinjectionapp.annotation.BasicAuthInterceptorOkHttpClient
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor constructor(private val username: String, private val password: String): Interceptor{
    private var creds: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authRequest = request.newBuilder()
            .header("Authorization", creds)
            .build()
        return chain.proceed(authRequest)
    }
}
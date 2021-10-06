package com.itamazons.interest_in_chat.WebTask
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null
    private const val KEY_AUTHORIZATION = "Authorization"
    private const val AUTHORIZATION_TOKEN = "563492ad6f91700001000001f7311efbe2f246c18dca5dc5b9ddec3a"
    fun getClient(): Retrofit {
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addNetworkInterceptor(object : Interceptor {
                @NotNull
                @Throws(IOException::class)
                override fun intercept(@NotNull chain: Interceptor.Chain): Response? {
                    val originalRequest: Request = chain.request()
                    val requestWithUserAgent: Request = originalRequest.newBuilder()
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/83.0.144 Chrome/77.0.3865.144 Safari/537.36")
                        .header(KEY_AUTHORIZATION, AUTHORIZATION_TOKEN)
                        .build()
                    return chain.proceed(requestWithUserAgent)
                }
            })
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

package br.com.meumetro.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Autowired
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class RetrofitClient @Autowired constructor(
        private val objectMapper: ObjectMapper
) {

    private val timeout = 40L

    fun instance(): Retrofit {
        val dateFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                Locale.getDefault()
        )
        objectMapper.dateFormat = dateFormat
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        val jacksonConverterFactory = JacksonConverterFactory.create(objectMapper)

        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl("http://apps.cptm.sp.gov.br:8080/AppMobileService/api/")
                .addConverterFactory(jacksonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

}
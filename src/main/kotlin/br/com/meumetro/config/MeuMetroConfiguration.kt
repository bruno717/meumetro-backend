package br.com.meumetro.config

import br.com.meumetro.network.RetrofitClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*


@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class, MongoReactiveDataAutoConfiguration::class])
@EnableReactiveMongoRepositories(basePackages = ["br.com.meumetro.repository"])
class MeuMetroConfiguration : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.database}")
    private val dataBase: String? = null

    @Value("\${spring.data.mongodb.database.name}")
    private val dataBaseName: String? = null

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Bean
    fun getModelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .matchingStrategy = MatchingStrategies.STRICT
        return modelMapper
    }

    @Bean
    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create(dataBase)
    }

    override fun getDatabaseName(): String {
        return dataBaseName ?: String()
    }

    @Bean
    fun getRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.messageConverters.add(StringHttpMessageConverter())
        return restTemplate
    }

    @Bean
    fun getSimpleDateFormat(): SimpleDateFormat {
        val pattern = "yyyy-MM-dd'T'hh:mm:ss"
        return SimpleDateFormat(pattern, Locale.getDefault())
    }

    @Bean
    fun getRetrofit(): Retrofit {
        return RetrofitClient(objectMapper).instance()

    }

}
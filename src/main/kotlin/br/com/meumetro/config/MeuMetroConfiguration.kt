package br.com.meumetro.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
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
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class, MongoReactiveDataAutoConfiguration::class])
@EnableReactiveMongoRepositories(basePackages = ["br.com.meumetro.repository"])
class MeuMetroConfiguration : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.database}")
    private val dataBase: String? = null

    @Value("\${spring.data.mongodb.database.name}")
    private val dataBaseName: String? = null

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

    @PostConstruct
    fun setupTimeZone() {
        val tz = TimeZone.getTimeZone("America/Sao_Paulo")
        TimeZone.setDefault(tz)
        GregorianCalendar.getInstance(tz)
    }
}
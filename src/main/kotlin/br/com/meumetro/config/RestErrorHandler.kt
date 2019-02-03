package br.com.meumetro.config

import br.com.meumetro.config.interceptors.InterceptorAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class RestErrorHandler(@Autowired private val interceptorAuth: InterceptorAuth) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptorAuth)
        super.addInterceptors(registry)
    }
}
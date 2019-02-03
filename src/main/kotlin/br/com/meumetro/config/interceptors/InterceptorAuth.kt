package br.com.meumetro.config.interceptors

import br.com.meumetro.security.SecurityManager
import br.com.meumetro.security.SecurityManager.SECURITY_KEY
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class InterceptorAuth : HandlerInterceptorAdapter() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (SecurityManager.isValidSecurityKey(request.getHeader(SECURITY_KEY))) {
            val messageError = "Erro de autorização"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, messageError)
            return true
        }
        return super.preHandle(request, response, handler)
    }
}
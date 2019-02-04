package br.com.meumetro.controller

import br.com.meumetro.service.AppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("app")
class AppController(@Autowired private val appService: AppService) {

    @GetMapping("version")
    fun verifyVersionApp(@RequestParam appVersion: String, response: HttpServletResponse) {

        if (!appService.isLatestVersion(appVersion)) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O meu metro está desatualizado")
        }
    }
}
package br.com.meumetro.service.impl

import br.com.meumetro.service.AppService
import org.springframework.stereotype.Service

@Service
class AppServiceImpl : AppService {

    private val versionApp = "1.4.20"

    override fun isLatestVersion(versionApp: String): Boolean {
        return this.versionApp == versionApp
    }
}
package br.com.meumetro.service

interface AppService {
    fun isLatestVersion(versionApp: String): Boolean
}
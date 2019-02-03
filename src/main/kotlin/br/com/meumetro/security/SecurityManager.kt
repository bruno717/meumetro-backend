package br.com.meumetro.security

object SecurityManager {

    const val SECURITY_KEY = "security_key"

    fun isValidSecurityKey(securityKey: String?): Boolean {
        return securityKey != null && securityKey.isNotEmpty() && securityKey == "e871f5a73c097ec22f27ebe917ed9f87"
    }
}
package br.com.meumetro.enums

enum class ConnectionType(val url: String) {

    PRODUCTION_STATUS_CPTM_V3("http://apps.cptm.sp.gov.br:8080/AppMobileService/api/linhasmetropolitanasAppV3?versao=3"),
    PRODUCTION_STATUS_CPTM_V4("http://apps.cptm.sp.gov.br:8080/AppMobileService/api/LinhasMetropolitanasAppV3?versao=4");
}
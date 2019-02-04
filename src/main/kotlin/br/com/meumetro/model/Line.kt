package br.com.meumetro.model

import br.com.meumetro.enums.LineType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Line {

    @Id
    val id = String()
    val lineType: LineType? = null
    val name: String? = null
    val situation: String? = null
    val description: String? = null
    val type: String? = null
    val modificationDate: String? = null

    companion object {
        const val PATTERN_DATE = "yyyy-MM-dd'T'HH:mm:ss"
        const val NORMAL_OPERATION = "Operacao normal"
        const val NORMAL_FINISHED = "Encerrada"
    }
}
package br.com.meumetro.model.dto

import br.com.meumetro.enums.LineType
import org.jsoup.nodes.Element

data class LineDTO(
        var id: String? = null,
        val lineType: LineType? = null,
        val name: String? = null,
        val situation: String? = null,
        val description: String? = null,
        val type: String? = null,
        val modificationDate: String? = null
) {
    constructor(response: LineResponseDTO) : this(
            null,
            LineType.getLineTypeByName(response.name ?: String()),
            LineType.getLineTypeByName(response.name ?: String())?.nameFormatted,
            response.status,
            response.description,
            response.type,
            response.generationDate
    )

        constructor(codeLine: String, statusLine: String, type: LineType) : this(
                null,
                type,
                LineType.getLineTypeByCode(codeLine)?.nameFormatted,
                statusLine,
                null,
                null,
                null
        )
}
package br.com.meumetro.model.dto

import br.com.meumetro.model.MetropolitanMap

data class MetropolitanMapDTO(
        val map: String,
        val modificationDate: String
) {

    constructor(metropolitanMap: MetropolitanMap) : this(
            metropolitanMap.map,
            metropolitanMap.modificationDate
    )

}
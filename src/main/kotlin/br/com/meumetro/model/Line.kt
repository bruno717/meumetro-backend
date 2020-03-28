package br.com.meumetro.model

import br.com.meumetro.enums.LineType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Lines")
class Line {

    @Id
    var id: String? = String()
    var lineType: LineType? = null
    var name: String? = null
    var situation: String? = null
    var description: String? = null
    var type: String? = null
    var modificationDate: String? = null

    companion object {
        const val PATTERN_DATE = "yyyy-MM-dd'T'HH:mm:ss"
        const val NORMAL_OPERATION = "Operacao normal"
        const val NORMAL_FINISHED = "Encerrada"
        const val INFORMATION_UNAVAILABLE = "Informacao indisponivel"
    }

}
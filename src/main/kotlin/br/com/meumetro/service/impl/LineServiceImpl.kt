package br.com.meumetro.service.impl

import br.com.meumetro.enums.ConnectionType
import br.com.meumetro.model.Line
import br.com.meumetro.model.dto.LineDTO
import br.com.meumetro.model.dto.LineResponseDTO
import br.com.meumetro.repository.LineRepository
import br.com.meumetro.service.LineService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

@Service
class LineServiceImpl @Autowired constructor(
        private val modelMapper: ModelMapper,
        private val repository: LineRepository,
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper
) : LineService {

    override fun updateLine(lineDTO: LineDTO): LineDTO {
        val line = modelMapper.map(lineDTO, Line::class.java)
        val lineUpdated = repository.save(line)
        return modelMapper.map(lineUpdated, LineDTO::class.java)
    }

    override fun getStatusLinesOnPageOfficial(): List<LineDTO> {

        val response = restTemplate.getForEntity(ConnectionType.PRODUCTION_STATUS_CPTM_V4.url, String::class.java)

        if (response.statusCode == HttpStatus.OK) {
            val body = response.body ?: String()
            return objectMapper.readValue<List<LineResponseDTO>>(body)
                    .map { LineDTO(it) }
        } else {
            throw ResponseStatusException(response.statusCode)
        }
    }

    override fun getLinesStatusByUser(): List<LineDTO> {
        // TODO: implementar getLinesStatusByUser()
    }

    override fun getLinesStatusByUserInDataBase(): ArrayList<LineDTO> {
        return repository.findAll()
                .collectList()
                .block()
                ?.map { modelMapper.map(it, LineDTO::class.java) } as ArrayList<LineDTO>
    }

    override fun getLineById(id: String): LineDTO {
        return repository.findById(id)
                .map { modelMapper.map(it, LineDTO::class.java) }
                .block() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, LINE_NOT_FOUND_BU_ID)
    }

    companion object {
        private const val LINE_NOT_FOUND_BU_ID = "line.not.found.by.id"
    }
}
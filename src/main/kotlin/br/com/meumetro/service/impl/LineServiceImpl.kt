package br.com.meumetro.service.impl

import br.com.meumetro.enums.ConnectionType
import br.com.meumetro.extensions.*
import br.com.meumetro.model.Line
import br.com.meumetro.model.dto.LineDTO
import br.com.meumetro.model.dto.LineResponseDTO
import br.com.meumetro.repository.LineRepository
import br.com.meumetro.service.LineService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.joda.time.DateTime
import org.joda.time.Duration
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Service
class LineServiceImpl @Autowired constructor(
        private val modelMapper: ModelMapper,
        private val repository: LineRepository,
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper
) : LineService {

    private val typeMetro = "M"
    private val typeViaQuatro = "4"
    private val typeViaMobilidade = "5"
    private val typeCPTM = "C"
    private var lineOfficialList: List<Line>? = null

    override fun updateLine(lineDTO: LineDTO): LineDTO {
        val line = modelMapper.map(lineDTO, Line::class.java)
        line.modificationDate = SimpleDateFormat(Line.PATTERN_DATE, Locale.US).format(Calendar.getInstance().time)
        val id = repository.findAll()
                .collectList()
                .block()
                ?.firstOrNull { it.lineType == line.lineType }
                ?.id
        line.id = id
        val lineUpdated = repository.save(line)
        lineUpdated.doOnError { throw it }

        val lineResponse = modelMapper.map(lineUpdated.block(), LineDTO::class.java)
        lineResponse.id = null
        return lineResponse
    }

    override fun getLinesStatusOnPageOfficial(): List<LineDTO> {

        val response = restTemplate.getForEntity(ConnectionType.PRODUCTION_STATUS_CPTM_V3.url, String::class.java)

        if (response.statusCode == HttpStatus.OK) {
            val body = response.body ?: String()
            return objectMapper.readValue<List<LineResponseDTO>>(body)
                    .map {
                        if (it.type == typeViaQuatro || it.type == typeViaMobilidade) {
                            it.type = typeMetro
                        }
                        LineDTO(it)
                    }
        } else {
            throw ResponseStatusException(response.statusCode)
        }
    }

    override fun getLinesStatusByUser(): List<LineDTO> {

        val listResponse = ArrayList<Line>()

        lineOfficialList = getLinesStatusOnPageOfficial()
                .map { modelMapper.map(it, Line::class.java) }

        val lineUsersList = repository.findAll()
                .collectList()
                .block()

        lineUsersList?.let { items ->
            items.filter { it.modificationDate?.isNotEmpty() == true }
                    .filter { isNecessaryChangeSituationLine(it) }
                    .forEach { changeSituationAndDate(it) }

            items.filter { it.modificationDate?.isEmpty() == true }
                    .forEach { changeSituationAndDate(it) }
        }
        lineUsersList?.forEach { it.id = null }

        val sortedLinesMetro = lineUsersList?.filter { it.type == typeMetro || it.type == typeViaQuatro }
                ?.sortedBy { it.lineType }
                ?.toMutableList() as ArrayList<Line>


        val sortedLinesCPTM = lineUsersList.filter { it.type == typeCPTM }
                .sortedBy { it.lineType }
                .toMutableList() as ArrayList<Line>

        listResponse.addAll(sortedLinesMetro)
        listResponse.addAll(sortedLinesCPTM)

        return listResponse.map { modelMapper.map(it, LineDTO::class.java) }
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
                .block() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, LINE_NOT_FOUND_BY_ID)
    }

    private fun isNecessaryChangeSituationLine(line: Line): Boolean {

        val lineOfficialList = getLinesStatusOnPageOfficial()
                .map { modelMapper.map(it, Line::class.java) }

        val format = SimpleDateFormat(Line.PATTERN_DATE, Locale.getDefault())

        val dateCurrent = Calendar.getInstance().time
        val dateLine = format.parse(line.modificationDate)

        val lineDate = DateTime(0, dateLine.getMonthFormatted(), dateLine.getDayFormatted(), dateLine.getHoursFormatted(), dateLine.getMinutesFormatted(), dateLine.getMinutesFormatted())
        val current = DateTime(0, dateCurrent.getMonthFormatted(), dateCurrent.getDayFormatted(), dateCurrent.getHoursFormatted(), dateCurrent.getMinutesFormatted(), dateCurrent.getMinutesFormatted())
        val minutes = Duration(lineDate, current).standardMinutes

        val lineOfficial = lineOfficialList.first { it.lineType == line.lineType }
        return minutes > 20 || lineOfficial.situation?.contains(CLOSED, true) == true
    }

    override fun getLinesToNotify(linesToNotify: List<LineDTO>): List<LineDTO> {
        return linesToNotify.filter {
            it.situation?.removeAccent()?.equals(Line.NORMAL_OPERATION, true) != true
                    && it.situation?.contains(Line.NORMAL_FINISHED) != true
                    && it.situation?.removeAccent()?.equals(Line.INFORMATION_UNAVAILABLE, true) != true
        }
    }

    private fun changeSituationAndDate(line: Line) {
        lineOfficialList?.let { items ->
            items.first { it.lineType == line.lineType }
                    .run {
                        line.situation = situation
                        line.description = description
                        line.modificationDate = SimpleDateFormat(Line.PATTERN_DATE, Locale.US).format(Calendar.getInstance().time)
                        repository.save(line).block()
                    }
        }
    }

    companion object {
        private const val CLOSED = "Encerrada"
        private const val LINE_NOT_FOUND_BY_ID = "line.not.found.by.id"
    }
}
package br.com.meumetro.controller

import br.com.meumetro.model.dto.LineDTO
import br.com.meumetro.service.LineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("status")
class LineController @Autowired constructor(private val service: LineService) {

    @PutMapping("update")
    fun updateLineForUser(@RequestBody lineDTO: LineDTO): LineDTO {
        return service.updateLine(lineDTO)
    }

    @GetMapping("official")
    fun getLinesStatusOfficial(): List<LineDTO> {
        return service.getStatusLinesOnPageOfficial()
    }
}
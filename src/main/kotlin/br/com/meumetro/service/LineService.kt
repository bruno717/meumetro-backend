package br.com.meumetro.service

import br.com.meumetro.model.dto.LineDTO

interface LineService {
    fun updateLine(lineDTO: LineDTO): LineDTO
    fun getLinesStatusByUserInDataBase(): ArrayList<LineDTO>
    fun getLineById(id: String): LineDTO
    fun getStatusLinesOnPageOfficial(): List<LineDTO>
    fun getLinesStatusByUser(): List<LineDTO>
}
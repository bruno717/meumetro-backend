package br.com.meumetro.service

import br.com.meumetro.model.dto.MetropolitanMapDTO

interface ImageMapService {

    fun retrieveMetropolitanMap(modificationDateClientString: Long?): MetropolitanMapDTO

}
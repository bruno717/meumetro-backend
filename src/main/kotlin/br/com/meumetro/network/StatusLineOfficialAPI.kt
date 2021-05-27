package br.com.meumetro.network

import br.com.meumetro.model.dto.LineResponseDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StatusLineOfficialAPI {

    @GET("linhasmetropolitanasAppV3")
    fun getLinesStatusOnPageOfficial(@Query("versao") version: String): Observable<List<LineResponseDTO>>

}
package br.com.meumetro.service

import br.com.meumetro.model.dto.DeviceDTO

interface DeviceService {
    fun saveDevice(deviceDTO: DeviceDTO): DeviceDTO
}
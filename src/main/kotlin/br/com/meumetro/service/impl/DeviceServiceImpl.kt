package br.com.meumetro.service.impl

import br.com.meumetro.model.Device
import br.com.meumetro.model.dto.DeviceDTO
import br.com.meumetro.repository.DeviceRepository
import br.com.meumetro.service.DeviceService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeviceServiceImpl @Autowired constructor(
        private val modelMapper: ModelMapper,
        private val repository: DeviceRepository
) : DeviceService {

    override fun saveDevice(deviceDTO: DeviceDTO): DeviceDTO {
        val device = modelMapper.map(deviceDTO, Device::class.java)
        val deviceSaved = repository.save(device)
        return modelMapper.map(deviceSaved.block(), DeviceDTO::class.java)
    }
}
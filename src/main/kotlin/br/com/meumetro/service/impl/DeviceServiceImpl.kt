package br.com.meumetro.service.impl

import br.com.meumetro.model.Device
import br.com.meumetro.model.Line.Companion.PATTERN_DATE
import br.com.meumetro.model.dto.DeviceDTO
import br.com.meumetro.repository.DeviceRepository
import br.com.meumetro.service.DeviceService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class DeviceServiceImpl @Autowired constructor(
        private val modelMapper: ModelMapper,
        private val repository: DeviceRepository
) : DeviceService {

    override fun saveDevice(deviceDTO: DeviceDTO): DeviceDTO {
        val device = modelMapper.map(deviceDTO, Device::class.java)
        device.creationDate = SimpleDateFormat(PATTERN_DATE, Locale.US).format(Date())
        val deviceSaved = repository.save(device).block()
        return modelMapper.map(deviceSaved, DeviceDTO::class.java)
    }
}
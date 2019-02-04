package br.com.meumetro.controller

import br.com.meumetro.model.dto.DeviceDTO
import br.com.meumetro.service.DeviceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("device")
class DeviceController @Autowired constructor(private val deviceService: DeviceService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveDevice(@RequestBody deviceDTO: DeviceDTO): DeviceDTO {
        return deviceService.saveDevice(deviceDTO)
    }
}
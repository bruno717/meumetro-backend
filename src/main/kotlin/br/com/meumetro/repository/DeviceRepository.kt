package br.com.meumetro.repository

import br.com.meumetro.model.Device
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : ReactiveCrudRepository<Device, String>
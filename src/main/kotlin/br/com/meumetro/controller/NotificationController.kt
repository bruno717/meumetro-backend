package br.com.meumetro.controller

import br.com.meumetro.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notification")
class NotificationController @Autowired constructor(private val service: NotificationService) {

    @GetMapping("send/status/lines")
    fun sendNotification() {
        service.verifyIfNeedSendMessage()
    }
}
package br.com.meumetro.service.impl

import br.com.meumetro.model.Line
import br.com.meumetro.model.dto.LineDTO
import br.com.meumetro.model.dto.NotificationMessageDTO
import br.com.meumetro.repository.DeviceRepository
import br.com.meumetro.service.LineService
import br.com.meumetro.service.NotificationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

@Service
class NotificationServiceImpl @Autowired constructor(
        private val lineService: LineService,
        private val deviceRepository: DeviceRepository,
        private val objectMapper: ObjectMapper
) : NotificationService {

    @Value("\${fmc.auth.key}")
    private val authKeyFcm: String? = null

    @Value("\${fmc.api.url}")
    private val apiUrlFcm: String? = null


    override fun verifyIfNeedSendMessage() {

        val linesToNotify = lineService.getLinesToNotify(lineService.getLinesStatusOnPageOfficial()) as ArrayList
        if (linesToNotify.isNotEmpty()) {
            sendNotification(NotificationType.MESSAGE_OFFICIAL_TYPE, linesToNotify)
        }

        linesToNotify.clear()
        linesToNotify.addAll(lineService.getLinesToNotify(lineService.getLinesStatusByUser()))

        if (linesToNotify.isNotEmpty()) {
            sendNotification(NotificationType.MESSAGE_BY_USER_TYPE, linesToNotify)
        }
    }

    private fun sendNotification(type: NotificationType, lineList: List<LineDTO>) {

//        val deviceToken = "dXXs_bDhxTM:APA91bFS6KgVyiqN9rCZ3aVE6OGa_uM7UlLsaVqF0ZJ554C4N7xNUF4kx7kVMFKUMh1g4wiTgL0sRQNXofuMaCLJ3ge_3nra2dglv7fscSdXWbZn3jurrHDkFN-iQpxg2AGxcaFaXdUc"

        val notification = NotificationMessageDTO()
        notification.message.title = getTitleMessage(type, lineList)
        if (lineList.size == 1) {
            notification.message.simpleDescription = lineList.first().situation ?: String()
        }

        notification.message.type = type.value
        notification.message.lines = lineList
        notification.message.date = SimpleDateFormat(Line.PATTERN_DATE, Locale.getDefault()).format(Date())
        notification.deviceList = getDeviceListToken()

        val json = objectMapper.writeValueAsString(notification)

        val url = URL(apiUrlFcm)
        val conn = url.openConnection() as HttpURLConnection

        conn.useCaches = false
        conn.doInput = true
        conn.doOutput = true

        conn.requestMethod = "POST"
        conn.setRequestProperty("Authorization", authKeyFcm)
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")

        val wr = OutputStreamWriter(conn.outputStream)
        wr.write(json)
        System.out.println(json)
        wr.flush()
        conn.inputStream

    }

    private fun getTitleMessage(type: NotificationType, lineList: List<LineDTO>): String {

        val title: String
        if (lineList.size > 1) {
            title = String.format(Locale.getDefault(), "%s - %s", "Meu Metro", if (type == NotificationType.MESSAGE_OFFICIAL_TYPE) "Oficial" else "Usuário")
        } else {
            title = String.format(Locale.getDefault(), "%s - %s", lineList.first().name, if (type == NotificationType.MESSAGE_OFFICIAL_TYPE) "Oficial" else "Usuário")
        }
        return title
    }

    private fun getDeviceListToken(): List<String> {
        val list = arrayListOf<String>()

        deviceRepository.findAll()
                .collectList()
                .block()
                ?.map { device ->
                    device.tokenDevice?.let { list.add(it) }
                }
        return list
    }

    enum class NotificationType(val value: Int) {
        MESSAGE_OFFICIAL_TYPE(1),
        MESSAGE_BY_USER_TYPE(2)
    }
}
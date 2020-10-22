package dev.qrivi.fapp.server.controller.dto.res

import java.time.ZonedDateTime

data class InfoDTO(
    val version: String,
    val clock: ZonedDateTime

) : Response()

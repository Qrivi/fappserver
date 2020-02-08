package dev.qrivi.fapp.server.dto.res

import com.fasterxml.jackson.annotation.JsonIgnore
import dev.qrivi.fapp.server.constants.SecurityConstants
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

data class AuthenticatedUser(
    override val httpStatus: HttpStatus = HttpStatus.OK,
    override val httpHeaders: HttpHeaders = HttpHeaders(),

    @JsonIgnore val authorization: String,

    val email: String,
    val name: String
) : Response() {
    init {
        httpHeaders.add(SecurityConstants.TOKEN_HEADER, this.authorization)
    }
}
package org.study.domains.auth.service

import org.slf4j.Logger
import org.springframework.stereotype.Service
import org.study.common.exception.CustomException
import org.study.common.exception.ErrorCode
import org.study.common.jwt.JwtProvider
import org.study.common.logging.Logging
import org.study.common.transaction.Transactional
import org.study.interfaces.OAuthServiceInterface

@Service
class AuthService (
    private val oAuth2Services: Map<String, OAuthServiceInterface>,
    private val jwtProvider: JwtProvider,
    private val logger: Logger = Logging.getLogger(AuthService::class.java),
    private val transaction: Transactional
) {

    fun handleAuth(state: String, code: String): String = Logging.logFor(logger) { log ->
        val provider = state.lowercase()
        log["provider"] = provider

        val callService = oAuth2Services[provider] ?: throw CustomException(ErrorCode.PROVIDER_NOT_FOUND, provider)

        val accessToken = callService.getToken(code)
        val userInfo = callService.getUserInfo(accessToken.accessToken)
        val token = jwtProvider.createToken(provider, userInfo.email, userInfo.name, userInfo.id)

        transaction.run {

        }

        return ""
    }
}
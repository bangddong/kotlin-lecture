package org.study.domains.auth.service

import org.springframework.stereotype.Service
import org.study.common.exception.CustomException
import org.study.common.exception.ErrorCode
import org.study.common.jwt.JwtProvider
import org.study.interfaces.OAuthServiceInterface

@Service
class AuthService (
    private val oAuth2Services: Map<String, OAuthServiceInterface>,
    private val jwtProvider: JwtProvider
) {
    fun handleAuth(state: String, code: String): String {
        val provider = state.lowercase()

        val callService = oAuth2Services[provider] ?: throw CustomException(ErrorCode.PROVIDER_NOT_FOUND, provider)

        val accessToken = callService.getToken(code)
        val userInfo = callService.getUserInfo(accessToken.accessToken)
        val token = jwtProvider.createToken(provider, userInfo.email, userInfo.name, userInfo.id)

        return ""
    }
}
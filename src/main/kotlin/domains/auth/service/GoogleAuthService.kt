package org.study.domains.auth.service

import org.springframework.stereotype.Service
import org.study.common.exception.CustomException
import org.study.common.exception.ErrorCode
import org.study.config.OAuth2Config
import org.study.interfaces.OAuth2TokenResponse
import org.study.interfaces.OAuth2UserResponse
import org.study.interfaces.OAuthServiceInterface

private const val key = "google"

@Service(key)
class GoogleAuthService(
    private val config: OAuth2Config
): OAuthServiceInterface {

    private val oAuthInfo = config.providers[key] ?: throw CustomException(ErrorCode.AUTH_CONFIG_NOT_FOUND)

    override val providerName: String = key
    override fun getToken(code: String): OAuth2TokenResponse {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(accessToken: String): OAuth2UserResponse {
        TODO("Not yet implemented")
    }

}
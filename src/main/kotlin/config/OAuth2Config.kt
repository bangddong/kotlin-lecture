package org.study.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/*
    oauth2:
        providers:
            google:
                client-id: your-google-client-id
                client-secret: your-google-client-secret
                redirect-uri: http://localhost:8080/auth/google/callback
            github:
                client-id: your-github-client-id
                client-secret: your-github-client-secret
                redirect-uri: http://localhost:8080/auth/github/callback
 */
@Configuration
@ConfigurationProperties(prefix = "oauth2")
class OAuth2Config {
    val providers: MutableMap<String, OAuth2ProviderValues> = mutableMapOf()
}

data class OAuth2ProviderValues(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
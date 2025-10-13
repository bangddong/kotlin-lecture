package org.study.domains.auth.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.study.types.entity.User

interface AuthUserRepository: JpaRepository<User, String> {
    fun existsByUsername(username: String): Boolean

    @Modifying
    @Query("UPDATE User SET accessToken = :accessToken WHERE username = :username")
    fun updateAccessTokenByUsername(
        @Param("username") username: String,
        @Param("accessToken") token: String
    )
}
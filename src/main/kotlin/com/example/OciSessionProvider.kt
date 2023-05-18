package com.example


import io.micronaut.context.annotation.Value
import io.micronaut.email.javamail.sender.MailPropertiesProvider
import io.micronaut.email.javamail.sender.SessionProvider
import jakarta.inject.Singleton
import jakarta.mail.Authenticator
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import java.util.*


@Singleton
class OciSessionProvider(
        mailPropertiesProvider: MailPropertiesProvider,
        @Value("\${app.ocimail.smtp.user}") user: String,
        @Value("\${app.ocimail.smtp.password}") password: String,
) : SessionProvider {

    private val properties: Properties
    private val user: String
    private val password: String

    init {
        properties = mailPropertiesProvider.mailProperties()
        this.user = user
        this.password = password
    }

    override fun session(): Session =
            Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication() = PasswordAuthentication(user, password)
            })
}

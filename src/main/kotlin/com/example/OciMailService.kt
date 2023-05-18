package com.example

import io.micronaut.context.event.StartupEvent
import io.micronaut.email.Email
import io.micronaut.email.EmailSender
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton


@Singleton
class OciMailService(
        private val emailSender: EmailSender<*, *>,
) {

    @EventListener
    fun onStartupEvent(startupEvent: StartupEvent) {
        send()
    }


    private fun send() {

        val builder = Email.builder()
                .from("noreply@exmaple.com")
                .subject("Test mail")
                .body("Test body")
                .to("test@example.com")

        emailSender.send(builder)

    }
}

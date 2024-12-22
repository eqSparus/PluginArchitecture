package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.events.Event

interface ResultPlugin<T : Event, R> {

    var name: String
    var next: ResultPlugin<T, R>?

    suspend fun run(event: T): R

    suspend fun process(event: T): R {
        return try {
            run(event)
        } catch (e: Exception) {
            rollback(event, e)
        }
    }

    fun convector(result: R, event: T): T = event

    suspend fun rollback(event: T, e: Exception? = null): R {
        throw e ?: RuntimeException("No exception")
    }

}

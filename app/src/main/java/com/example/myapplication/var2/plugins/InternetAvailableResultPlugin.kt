package com.example.myapplication.var2.plugins

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.ResultPlugin
import java.lang.IllegalStateException

class InternetAvailableResultPlugin<T : Event, R>(
    override var next: ResultPlugin<T, R>?,
    private val isInternet: Boolean,
) : ResultPlugin<T, R> {

    override var name: String =
        next?.name ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")

    override suspend fun run(event: T): R =
        if (isInternet) {
            println("Проверка на интернетъ")
            next?.process(event)
                ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
        } else {
            throw IllegalStateException("Интернета нетъ")
        }

}
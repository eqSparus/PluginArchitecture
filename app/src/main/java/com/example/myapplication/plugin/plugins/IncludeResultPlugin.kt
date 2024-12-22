package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.plugin.events.Event

class IncludeResultPlugin<T : Event, R>(
    override var next: ResultPlugin<T, R>?,
    private val config: ResultPluginConfig,
    private val defaultValue: R,
) : ResultPlugin<T, R> {

    override var name: String =
        next?.name ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")


    override suspend fun run(event: T): R =
        if (config.isEnabled) {
            println("Плагинъ $name включенъ")
            next?.process(event)
                ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
        } else {
            defaultValue
        }
}
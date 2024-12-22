package com.example.myapplication.plugin.decorators

import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin

class IncludedDecorator<T : Event>(
    override val plugin: Plugin<T>,
    private val config: PluginConfig,
) : PluginDecorator<T> {

    override suspend fun run(event: T) {
        if (config.isEnabled) {
            println("Плагинъ $name включенъ")
            plugin.process(event)
        } else {
            println("Плагинъ $name отключенъ")
        }
    }

}
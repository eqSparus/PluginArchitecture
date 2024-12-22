package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.plugin.events.Event

interface ResultPluginFactory<T : Event, R> {

    val basePlugin: ResultPlugin<T, R>
    val config: ResultPluginConfig
    val decorators: Map<String, OptionalResultPluginFactory>
    val defaultValue: R

    fun createPlugin(): ResultPlugin<T, R> {
        var basePlugin = basePlugin
        basePlugin.name = config.pluginName

        config.decorators.filterValues { it }.keys
            .mapNotNull { decorators[it] }
            .forEach {
                basePlugin = it.createOptionalResultPlugin(basePlugin)
            }

        return IncludeResultPlugin(basePlugin, config, defaultValue)
    }

}
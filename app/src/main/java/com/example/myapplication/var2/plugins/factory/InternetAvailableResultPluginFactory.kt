package com.example.myapplication.var2.plugins.factory

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.OptionalResultPluginFactory
import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.var2.plugins.InternetAvailableResultPlugin

class InternetAvailableResultPluginFactory : OptionalResultPluginFactory {

    override fun <T : Event, R> createOptionalResultPlugin(plugin: ResultPlugin<T, R>): ResultPlugin<T, R> {
        return InternetAvailableResultPlugin(plugin, true)
    }
}
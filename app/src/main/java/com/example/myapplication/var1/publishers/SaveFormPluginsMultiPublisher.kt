package com.example.myapplication.var1.publishers

import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.publishers.PluginsPublisher
import com.example.myapplication.var1.events.SaveFormEvent

class SaveFormPluginsMultiPublisher(private val plugins: Set<Plugin<SaveFormEvent>>) :
    PluginsPublisher<SaveFormEvent> {

    override suspend fun onPublishEvent(event: SaveFormEvent) {
        plugins.sortedBy { it.order }.forEach { it.process(event) }
    }
}
package com.example.myapplication.var1.publishers

import com.example.myapplication.plugin.decorators.PluginDecorator
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.publishers.PluginsPublisher
import com.example.myapplication.var1.events.SaveFormEvent

class SaveFormPluginsSinglePublisher(private val plugins: Set<Plugin<SaveFormEvent>>) :
    PluginsPublisher<SaveFormEvent> {

    override suspend fun onPublishEvent(event: SaveFormEvent) {
        plugins.find { it.name == event.type.title }?.process(event)
    }

//    override suspend fun onPublishEvent(event: SaveFormEvent) {
//        when (event.type) {
//            SaveType.LOCAL -> plugins.find { it.getBasePlugin() is SaveFormLocalPlugin }
//                ?.process(event)
//
//            SaveType.SERVER -> plugins.find { it.getBasePlugin() is SaveFormServerPlugin }
//                ?.process(event)
//        }
//    }

}

inline fun <reified T : Event> Plugin<T>.getBasePlugin(): Plugin<T> {
    var plugin = this
    while (plugin is PluginDecorator<T>) {
        plugin = plugin.plugin
    }
    return plugin
}
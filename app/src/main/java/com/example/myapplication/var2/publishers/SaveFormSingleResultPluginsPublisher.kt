package com.example.myapplication.var2.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.ResultPluginsPublisher
import com.example.myapplication.var2.events.SaveFormResultEvent

class SaveFormSingleResultPluginsPublisher(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : ResultPluginsPublisher<SaveFormResultEvent, String> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): String =
        plugins.find { it.name == event.type.title }?.process(event)
            ?: throw IllegalArgumentException("Плагнъ не найденъ")
}
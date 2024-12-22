package com.example.myapplication.var2.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.ResultPluginsPublisher
import com.example.myapplication.var2.events.SaveFormResultEvent

class SaveFormMultiResultPluginPublisher(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : ResultPluginsPublisher<SaveFormResultEvent, List<String>> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): List<String> {
        val result = mutableListOf<String>()
        plugins.forEach {
            try {
                result.add(it.process(event))
            } catch (e: Exception) {
                System.err.println()
            }
        }
        return result
    }

}

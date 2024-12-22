package com.example.myapplication.var2.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.ResultPluginsPublisher
import com.example.myapplication.plugin.utils.createChain
import com.example.myapplication.var2.events.SaveFormResultEvent

class SaveFormChainResultPluginPublisher(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : ResultPluginsPublisher<SaveFormResultEvent, String> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): String {
        val all = plugins.toMutableSet()
        val first = all.first()
        all.remove(first)
        return createChain(first, *all.toTypedArray()).process(event)
    }

}
package com.example.myapplication.var2.plugins.factory

import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.plugin.plugins.OptionalResultPluginFactory
import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.plugins.ResultPluginFactory
import com.example.myapplication.var2.events.SaveFormResultEvent
import com.example.myapplication.var2.plugins.SaveLocalResultPlugin

class SaveFormLocalResultPluginFactory(
    override val config: ResultPluginConfig,
    override val decorators: Map<String, OptionalResultPluginFactory>,
) : ResultPluginFactory<SaveFormResultEvent, String> {

    override val basePlugin: ResultPlugin<SaveFormResultEvent, String> = SaveLocalResultPlugin()
    override val defaultValue: String = ""

}
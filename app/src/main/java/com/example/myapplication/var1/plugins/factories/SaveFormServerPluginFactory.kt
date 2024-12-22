package com.example.myapplication.var1.plugins.factories

import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.plugins.PluginFactory
import com.example.myapplication.var1.events.SaveFormEvent
import com.example.myapplication.var1.plugins.SaveFormServerPlugin

class SaveFormServerPluginFactory(
    override val config: PluginConfig,
    override val decorators: Map<String, DecoratorFactory>
) : PluginFactory<SaveFormEvent> {

    override val basePlugin: Plugin<SaveFormEvent> = SaveFormServerPlugin()

}
package com.example.myapplication.var1.configs

import com.example.myapplication.plugin.configs.PluginConfig

class SaveFormLocalConfig : PluginConfig {
    override var pluginName = "SaveFormLocal"
    override var isEnabled = true
    override var order = 0
    override var decorators = mapOf(
        "isPremium" to false,
        "isInternet" to false,
    )
}
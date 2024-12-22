package com.example.myapplication.var1.configs

import com.example.myapplication.plugin.configs.AppPluginConfiguration
import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.var2.configs.SaveFormLocalResultConfig
import com.example.myapplication.var2.configs.SaveFormServerResultConfig

class AppConfigurationImpl private constructor(
    private val config: Set<PluginConfig>,
    private val resultConfig: Set<ResultPluginConfig>,
) :
    AppPluginConfiguration {


    override fun getConfig(pluginName: String): PluginConfig? =
        config.find { it.pluginName == pluginName }

    override fun getResultConfig(pluginName: String): ResultPluginConfig? =
        resultConfig.find { it.pluginName == pluginName }

    companion object {
        private var config: AppPluginConfiguration? = null
        fun newInstance(): AppPluginConfiguration = config ?: AppConfigurationImpl(
            setOf(SaveFormLocalConfig(), SaveFormServerConfig()),
            setOf(SaveFormServerResultConfig(), SaveFormLocalResultConfig()),
        )
    }
}
package com.example.myapplication

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.publishers.PluginsPublisher
import com.example.myapplication.var1.configs.AppConfigurationImpl
import com.example.myapplication.var1.decorators.factories.InternetDecoratorFactory
import com.example.myapplication.var1.decorators.factories.PremiumDecoratorFactory
import com.example.myapplication.var1.events.Form
import com.example.myapplication.var1.events.SaveFormEvent
import com.example.myapplication.var1.plugins.factories.SaveFormLocalPluginFactory
import com.example.myapplication.var1.plugins.factories.SaveFormServerPluginFactory
import com.example.myapplication.var1.publishers.SaveFormPluginsMultiPublisher
import com.example.myapplication.var1.publishers.SaveFormPluginsSinglePublisher
import kotlinx.coroutines.runBlocking

private val decoratorsFactory = mapOf(
    "isInternet" to InternetDecoratorFactory(),
    "isPremium" to PremiumDecoratorFactory(),
)
private val config = AppConfigurationImpl.newInstance()

fun main() = runBlocking {
    val serverPlugin = config.getConfig("SaveFormServer")?.let {
        SaveFormServerPluginFactory(it, decoratorsFactory)
    }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val localPlugin = config.getConfig("SaveFormLocal")?.let {
        SaveFormLocalPluginFactory(it, decoratorsFactory)
    }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val plugins = setOf(serverPlugin, localPlugin)

    val event = SaveFormEvent(Form("1", "form"))

    runPublisher(SaveFormPluginsSinglePublisher(plugins), event)
    println("====================")
    runPublisher(SaveFormPluginsMultiPublisher(plugins), event)
}

private suspend fun <T : Event> runPublisher(publisher: PluginsPublisher<T>, event: T) {
    try {
        publisher.onPublishEvent(event)
    } catch (e: Exception) {
        System.err.println(e.message)
    }
}
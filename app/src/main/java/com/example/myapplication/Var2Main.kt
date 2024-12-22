package com.example.myapplication

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.publishers.ResultPluginsPublisher
import com.example.myapplication.var1.configs.AppConfigurationImpl
import com.example.myapplication.var1.events.Form
import com.example.myapplication.var2.events.SaveFormResultEvent
import com.example.myapplication.var2.plugins.factory.InternetAvailableResultPluginFactory
import com.example.myapplication.var2.plugins.factory.PremiumAvailableResultPluginFactory
import com.example.myapplication.var2.plugins.factory.SaveFormLocalResultPluginFactory
import com.example.myapplication.var2.plugins.factory.SaveFormServerResultPluginFactory
import com.example.myapplication.var2.publishers.SaveFormChainResultPluginPublisher
import com.example.myapplication.var2.publishers.SaveFormMultiResultPluginPublisher
import com.example.myapplication.var2.publishers.SaveFormSingleResultPluginsPublisher
import kotlinx.coroutines.runBlocking

private val config = AppConfigurationImpl.newInstance()

private val optionalPlugins = mapOf(
    "isPremium" to PremiumAvailableResultPluginFactory(),
    "isInternet" to InternetAvailableResultPluginFactory(),
)

fun main() = runBlocking {
    val saveFormEvent = SaveFormResultEvent(Form("1", "Названіе"))

    val saveFormResultServerPluginFactory =
        config.getResultConfig("SaveFormServerResultPlugin")?.let {
            SaveFormServerResultPluginFactory(it, optionalPlugins)
        }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val saveFormResultLocalPluginFactory =
        config.getResultConfig("SaveFormLocalResultPlugin")?.let {
            SaveFormLocalResultPluginFactory(it, optionalPlugins)
        }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val plugins = setOf(saveFormResultLocalPluginFactory, saveFormResultServerPluginFactory)

    runResultPublisher(SaveFormSingleResultPluginsPublisher(plugins), saveFormEvent)
    println("=======================")
    runResultPublisher(SaveFormMultiResultPluginPublisher(plugins), saveFormEvent)
    println("=======================")
    runResultPublisher(SaveFormChainResultPluginPublisher(plugins), saveFormEvent)
}

private suspend inline fun <reified T : Event, reified R> runResultPublisher(
    publisher: ResultPluginsPublisher<T, R>,
    event: T
) {
    try {
        println(publisher.onGetPublishResult(event))
    } catch (e: Exception) {
        System.err.println(e.message)
    }
}



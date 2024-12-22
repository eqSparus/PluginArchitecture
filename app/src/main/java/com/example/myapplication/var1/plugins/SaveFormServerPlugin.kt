package com.example.myapplication.var1.plugins

import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.var1.events.SaveFormEvent

class SaveFormServerPlugin : Plugin<SaveFormEvent> {

    override var order: Int = 1
    override var name: String = this::class.simpleName.toString()

    override suspend fun run(event: SaveFormEvent) {
        println("Сохранено на серверѣ")
    }
}
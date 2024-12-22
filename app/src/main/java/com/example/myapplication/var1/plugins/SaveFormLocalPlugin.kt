package com.example.myapplication.var1.plugins

import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.var1.events.SaveFormEvent

class SaveFormLocalPlugin : Plugin<SaveFormEvent> {

    override var order: Int = 0
    override var name: String = this::class.simpleName.toString()

    override suspend fun run(event: SaveFormEvent) {
        println("Сохранено локально")
    }

    override suspend fun rollback(event: SaveFormEvent, e: Exception?) {
        println("Удалено локально")
        super.rollback(event, e)
    }
}
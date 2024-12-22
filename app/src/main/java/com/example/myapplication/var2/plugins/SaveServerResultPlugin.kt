package com.example.myapplication.var2.plugins

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.var2.events.SaveFormResultEvent

class SaveServerResultPlugin : ResultPlugin<SaveFormResultEvent, String> {

    override var name: String = this::class.simpleName.toString()
    override var next: ResultPlugin<SaveFormResultEvent, String>? = null

    override suspend fun run(event: SaveFormResultEvent): String {
        println("Сохранено на серверѣ")
        return next?.process(event) ?: "2"
    }


}
package com.example.myapplication.var2.plugins

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.var2.events.SaveFormResultEvent


class SaveLocalResultPlugin : ResultPlugin<SaveFormResultEvent, String> {

    override var name: String = this::class.simpleName.toString()

    override var next: ResultPlugin<SaveFormResultEvent, String>? = null

    override suspend fun run(event: SaveFormResultEvent): String {
        println("Сохранено локально")
        return next?.process(event) ?: "1"
    }

    override suspend fun rollback(event: SaveFormResultEvent, e: Exception?): String {
        println("Откатъ локально")
        return event.form.id
    }

    override fun convector(result: String, event: SaveFormResultEvent): SaveFormResultEvent =
        event.copy(form = event.form.copy(id = result))
}
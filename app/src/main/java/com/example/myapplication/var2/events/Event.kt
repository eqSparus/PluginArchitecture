package com.example.myapplication.var2.events

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.var1.events.Form

data class SaveFormResultEvent(
    val form: Form,
    val type: SaveTypeResult = SaveTypeResult.SAVE_FORM_LOCAL,
) : Event

enum class SaveTypeResult (val title: String){
    SAVE_FORM_LOCAL("SaveFormLocalResultPlugin"), SAVE_FORM_SERVER("SaveFormServerResultPlugin");
}

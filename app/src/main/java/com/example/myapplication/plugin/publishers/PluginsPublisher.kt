package com.example.myapplication.plugin.publishers

import com.example.myapplication.plugin.events.Event

interface PluginsPublisher<T : Event> {

    suspend fun onPublishEvent(event: T)

}
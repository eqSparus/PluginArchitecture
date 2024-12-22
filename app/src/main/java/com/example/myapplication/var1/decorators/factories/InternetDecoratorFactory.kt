package com.example.myapplication.var1.decorators.factories

import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.var1.decorators.InternetAvailableDecorator

class InternetDecoratorFactory : DecoratorFactory {
    override fun <T : Event> createDecorator(plugin: Plugin<T>): Plugin<T> {
        return InternetAvailableDecorator(plugin, true)
    }
}
package com.example.myapplication.plugin.publishers

interface ResultPluginsPublisher<T, R> {

    suspend fun onGetPublishResult(event: T): R

}
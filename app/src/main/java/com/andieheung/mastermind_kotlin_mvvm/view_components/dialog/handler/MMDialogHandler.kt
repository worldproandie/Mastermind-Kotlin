package com.andieheung.mastermind_kotlin_mvvm.view_components.dialog.handler

interface MMDialogHandler {
    fun clickOk()
}

interface MMDialogCancellableHandler:MMDialogHandler{
    fun clickCancel()
}
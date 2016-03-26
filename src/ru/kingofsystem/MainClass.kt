package ru.kingofsystem

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import ru.kingofsystem.controllers.MainController

fun main(args: Array<String>) {
    val app = Main()
    app.startApp(args)
}

var changeBytes: EventHandler<MouseEvent>? = null


class Main : Application() {

    fun startApp(args: Array<String>) {
        launch(*args)
    }

    override fun start(stage: Stage?) {
        val loader  = FXMLLoader()
        loader.location = Main::class.java.getResource("/views/main.fxml")
        val pane: AnchorPane = loader.load()
        val ctrl: MainController = loader.getController()
        val scene = Scene(pane)
        stage?.scene = scene
        stage?.show()
    }

}
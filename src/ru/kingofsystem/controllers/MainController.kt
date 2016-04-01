package ru.kingofsystem.controllers

import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.*
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import jssc.SerialPortList
import ru.kingofsystem.changeBytes
import ru.kingofsystem.resetAnchor
import kotlin.concurrent.timer
/**
 * Created by tamtaradam on 24.03.16.
 */
class MainController {

    @FXML
    private lateinit var notationPane: AnchorPane
    @FXML
    private lateinit  var exitBtn: Button
    @FXML
    private lateinit var mcuBtn: Button
    @FXML
    private lateinit var clipboardBtn: Button
    @FXML
    private lateinit var comChoice: ChoiceBox<String>
    @FXML
    private lateinit var baudChoice: TextField
    @FXML
    private lateinit var alphabetBox: AnchorPane
    @FXML
    private lateinit var menuBar: MenuBar
    @FXML
    private lateinit var lcdPane: AnchorPane

    private lateinit var alphabetController: AlphabetController

    @FXML
    private fun initialize() {
        alphabetBox.isMouseTransparent
        changeBytes = onSymbolUpdate
        initAlphabet()
        mcuBtn.onMouseClicked = onMcuClicked
        clipboardBtn.onMouseClicked = onClipboard
        alphabetBox.addEventFilter(MouseEvent.MOUSE_CLICKED, { e:MouseEvent ->
            val ctrl = alphabetController.getLcdSymbolCtrl(e.sceneX, e.sceneY)
            ctrl?.getBytes()?.map { i -> print(i) }
            println()
        })
        initPorts()
    }

    private fun initAlphabet() {
        // TODO Add alphabet to args
        val loader = FXMLLoader(MainController::class.java.getResource("/views/alphabet_pane.fxml"))
        val pane: HBox = loader.load()
        alphabetBox.children.add(pane)
        resetAnchor(pane)
        alphabetController = loader.getController()
        alphabetController.setSymbolsCount(8) // FIXME Need to be in args
    }

    private val onSymbolUpdate = EventHandler {e:MouseEvent ->

    }

    private val onMcuClicked = EventHandler { e:MouseEvent ->
        val alphabet = alphabetController.getAlphabet()
        alphabet.map { arr ->
            var str = ""
            arr.map { b ->  str += Integer.toHexString(b.toInt()) + " " }
            println(str)
        }
    }

    private val onClipboard = EventHandler {e:MouseEvent ->
        throw NotImplementedError()
    }

    private fun initPorts() {
        timer(name = "Com updater", daemon = true, period = 1000, action = {
            val portList: Array<String> = SerialPortList.getPortNames()
            Platform.runLater {
                val list = FXCollections.observableArrayList<String>()
                list.addAll(*portList)
                comChoice.items = list
            }
        })
    }
}
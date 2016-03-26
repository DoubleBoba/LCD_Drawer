package ru.kingofsystem.controllers

import javafx.beans.value.ChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import jssc.SerialPortList
import ru.kingofsystem.Main
import ru.kingofsystem.changeBytes
import ru.kingofsystem.resetAnchor
import javax.rmi.CORBA.Util

/**
 * Created by tamtaradam on 24.03.16.
 */
class MainController {

    @FXML
    private var symbolPane: AnchorPane? = null
    private var symbolCtrl: LcdSymbolController? = null

    @FXML
    private var notationPane: AnchorPane? = null
    @FXML
    private var exitBtn: Button? = null
    @FXML
    private var mcuBtn: Button? = null
    @FXML
    private var clipboardBtn: Button? = null
    @FXML
    private var hexNotation: Label? = null
    @FXML
    private var comChoice: ChoiceBox<String>? = null
    @FXML
    private var baudChoice: TextField? = null

    @FXML
    private fun initialize() {
        symbolPane?.isPickOnBounds = true
        symbolPane?.isMouseTransparent = false
        changeBytes = onSymbolUpdate
        initSymbol()
        mcuBtn?.onMouseClicked = onMcuClicked
        clipboardBtn?.onMouseClicked = onClipboard
        symbolPane?.onMouseClicked = onSymbolUpdate

        initPorts()
    }

    private fun initSymbol() {
        val loader = FXMLLoader(MainController::class.java.getResource("/views/lcd_symbol.fxml"))
        val smbl: VBox = loader.load()
        resetAnchor(smbl)
        symbolPane?.children?.add(smbl)
        symbolCtrl = loader.getController()


    }

    private val onSymbolUpdate = EventHandler {e:MouseEvent ->
        val bytes: Array<Byte>? = symbolCtrl?.getBytes()
        var str = ""
        bytes?.map { i ->
            str += Integer.toHexString(i.toInt()) + ", "
        }
        hexNotation?.text = str.toUpperCase().substring(0, str.length - 2)
    }

    private val onMcuClicked = EventHandler { e:MouseEvent ->
        val bytes = symbolCtrl?.getBytes()
        bytes?.map { i ->
            print(Integer.toBinaryString(i.toInt()))
            print(" ")
        }
        println()
    }

    private val onClipboard = EventHandler {e:MouseEvent ->
        val clipboard = Clipboard.getSystemClipboard()
        val content = ClipboardContent()
        content.putString(hexNotation?.text)
        clipboard.setContent(content)
    }

    private fun initPorts() {
        val portList: Array<String> = SerialPortList.getPortNames()
        comChoice?.items?.addAll(*portList)

    }
}
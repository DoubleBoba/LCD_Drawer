package ru.kingofsystem.controllers

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import ru.kingofsystem.Main
import ru.kingofsystem.resetAnchor
import java.lang.Byte
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
    private fun initialize() {
        print("fsdfsd")
        println(symbolPane)
        initSymbol()
        mcuBtn?.onMouseClicked = onMcuClicked
    }

    private fun initSymbol() {
        val loader = FXMLLoader(MainController::class.java.getResource("/views/lcd_symbol.fxml"))
        val smbl: VBox = loader.load()
        resetAnchor(smbl)
        symbolPane?.children?.add(smbl)
        symbolCtrl = loader.getController()
    }

    private val onMcuClicked: EventHandler<MouseEvent> = EventHandler { e:MouseEvent ->
        val bytes = symbolCtrl?.getBytes()
        bytes?.map { i ->
            print(Integer.toBinaryString(i.toInt()))
            print(" ")
        }
        println()
    }

}
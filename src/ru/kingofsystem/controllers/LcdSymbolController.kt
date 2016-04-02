package ru.kingofsystem.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import ru.kingofsystem.resetAnchor
import java.util.*

/**
 * Created by tamtaradm on 23.03.16.
 */
class LcdSymbolController {
    @FXML
    private lateinit  var rows: VBox

    private var rowsControllers: ArrayList<LcdRowController> = ArrayList()

    @FXML
    private fun initialize() {
        createRows()
    }

    private fun createRows() {
        repeat(8, {i: Int ->
            val loader = FXMLLoader()
            loader.location = LcdRowController::class.java.getResource("/views/lcd_row.fxml")
            val pane: HBox = loader.load()
            VBox.setVgrow(pane, Priority.SOMETIMES)
            rows.children.add(pane)
            val controller: LcdRowController = loader.getController()
            rowsControllers.add(controller)
        })
    }

    fun getBytes(): Array<Byte> {
        return Array(rowsControllers.size, {i ->
            return@Array rowsControllers[i].getBits()
        })
    }

    fun bindSymbol(symbol: LcdSymbolController) {
        rowsControllers.mapIndexed { i, lcdRowController -> lcdRowController.getByteProperty().bind(symbol.rowsControllers[i].getByteProperty())}
    }

}
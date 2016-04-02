package ru.kingofsystem.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.util.*

/**
 * Created by tamtaradm on 01.04.16.
 */
class LcdDisplayController {
    @FXML
    private lateinit var container: VBox

    private lateinit var displayRows: Array<HBox>

    private lateinit var ctrls: Array<Array<LcdSymbolController>>

    @FXML
    private fun initialize(){

    }

    fun setSize(rows:Int, columns:Int) {
        if (displayRows != null) {
            displayRows.map { it -> it.children.removeAll() }
        }
        displayRows = Array(columns, {return@Array HBox()})
        ctrls = Array(rows, { i ->
            return@Array  Array(columns, symbols@{ j ->
                return@symbols createSymbol(displayRows[i])
            })
        })
    }
    private fun createSymbol(container:HBox):LcdSymbolController {
        val loader = FXMLLoader(AlphabetController::class.java.getResource("/views/lcd_symbol.fxml"))
        val symbol: VBox = loader.load()
        container.children.add(symbol)
        return loader.getController()
    }

    fun assignSymbol(bytes: Array<Byte>, x:Double, y:Double) {
        container.children.mapIndexed { i, node ->
            (node as HBox).children.mapIndexed { j, node ->
                val symbol = node as VBox
                if (symbol.localToScene(symbol.boundsInLocal).contains(x, y)) {

                }
            }
        }
    }
}
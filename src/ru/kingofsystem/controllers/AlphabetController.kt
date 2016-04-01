package ru.kingofsystem.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.util.*

/**
 * Created by tamtaradm on 31.03.16.
 */
class AlphabetController {
    @FXML
    private lateinit var box: HBox

    @FXML
    private val ctrls = ArrayList<LcdSymbolController>()

    @FXML
    private fun initialize() {

    }

    fun setSymbolsCount(count: Int) {
        val size = box.children.size
        if (size < count) {
            repeat(count - size, {createSymbol()})
        } else if (size > count) {
            repeat(size - count, {
                box.children.removeAt(box.children.size - 1)
                ctrls.removeAt(ctrls.size - 1)
            })
        }
        (box.children.last() as VBox).padding = Insets.EMPTY
    }

    private fun createSymbol() {
        val loader = FXMLLoader(AlphabetController::class.java.getResource("/views/lcd_symbol.fxml"))
        val symbol: VBox = loader.load()
        symbol.padding = Insets(0.toDouble(), 3.toDouble(), 0.toDouble(), 0.toDouble())
        box.children.add(symbol)
        val ctrl: LcdSymbolController = loader.getController()
        ctrls.add(ctrl)
    }

    fun getAlphabet(): Array<Array<Byte>> {
        val arr: Array<Array<Byte>> = Array(box.children.size, { i: Int ->
            return@Array ctrls[i].getBytes()
        })
        return arr
    }

    fun getLcdSymbolCtrl(x:Double, y:Double): LcdSymbolController? {
        box.children.mapIndexed { i, node ->
            val node = node as VBox
            if (node.localToScene(node.boundsInLocal).contains(x, y)) {
                return ctrls[i]
            }
        }
        return null
    }
}
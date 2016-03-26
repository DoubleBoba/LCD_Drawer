package ru.kingofsystem.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.control.ToggleButton
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import ru.kingofsystem.changeBytes

/**
 * Created by tamtaradm on 23.03.16.
 */
class LcdRowController {

    @FXML
    private var pane: HBox? = null

    @FXML
    private fun initialize() {
        var i = 0
        do {
            val btn = create_btn()
            pane?.children?.add(btn)
            btn.onMouseClicked = changeBytes
        }while((++i) < 5)

    }

    private fun create_btn(): ToggleButton {
        val loader = FXMLLoader()
        loader.location = LcdRowController::class.java.getResource("/views/lcd_cell.fxml")
        val btn: ToggleButton = loader.load()
        HBox.setHgrow(btn, Priority.SOMETIMES)
        return btn
    }

    fun getBits(): Byte {
        var b = 0
        pane?.children?.map {x: Node ->
            val btn = x as ToggleButton
            b = if (btn.isSelected) (b shl 1) or 1 else (b shl 1)
        }
        return b.toByte()
    }

}

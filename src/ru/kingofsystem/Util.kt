package ru.kingofsystem

import javafx.scene.Node
import javafx.scene.layout.AnchorPane

/**
 * Created by tamtaradam on 24.03.16.
 */

fun resetAnchor(n: Node) {
    AnchorPane.setLeftAnchor(n, 0.toDouble())
    AnchorPane.setBottomAnchor(n, 0.toDouble())
    AnchorPane.setRightAnchor(n, 0.toDouble())
    AnchorPane.setTopAnchor(n, 0.toDouble())
}
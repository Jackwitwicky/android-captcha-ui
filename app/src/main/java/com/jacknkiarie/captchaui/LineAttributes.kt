package com.jacknkiarie.captchaui

class LineAttributes(var startingXCoordinate: Float, var startingYCoordinate: Float, var endingXCoordinate: Float, var endingYCoordinate : Float) {

    override fun toString(): String {
        return "Xtop: $startingXCoordinate, YTop: $startingYCoordinate, XEnd: $endingXCoordinate, YEnd: $endingYCoordinate"
    }

}
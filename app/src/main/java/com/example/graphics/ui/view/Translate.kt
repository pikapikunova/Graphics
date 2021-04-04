package com.example.graphics.ui.view

import kotlin.math.abs

class Translate(var x1: Float, var x2: Float, var y1: Float, var y2: Float, val width: Float, val height: Float){


    fun transformationX(): Float?{
        //количество пикселей в единичном отрезке
        val unitY = height/(y2-y1)

        var tempYX: Float? = null

        if(y2>-50 && y1<50)
            //на сколько нужно сдвинуть ось OX по y
            tempYX = unitY*abs(y2)

        return (tempYX)
    }

    fun transformationY(): Float?{
        //количество пикселей в единичном отрезке
        val unitX= width/(x2-x1)

        var tempXY: Float? = null

        if(x1>-50 && x2<50)
            //на сколько нужно сдвинуть ось OY по x
            tempXY = unitX*abs(x1)

        return (tempXY)
    }


    fun unY(): Float{
        //количество пикселей в единичном отрезке
        return height/(y2-y1)
    }

    fun unX(): Float{
        //количество пикселей в единичном отрезке
        return width/(x2-x1)
    }

    fun someY(y: Float): Float?{
        //перевод в экранную систему координат
        if(y>=y1 && y<=y2)
            return(y2-y)*unY()
        else
            return null
    }

    fun someX(x: Float): Float?{
        //перевод в экранную систему координат
        if(x>=x1 && x<=x2)
            return(abs(x1-x))*unX()
        else
            return null
    }
}
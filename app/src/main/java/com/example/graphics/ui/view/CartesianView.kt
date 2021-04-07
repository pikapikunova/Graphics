package com.example.graphics.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.ln

class CartesianView(context : Context, attrs: AttributeSet?) : View(context, attrs) {
    constructor(context: Context) : this(context, null)

    val paintBG: Paint = Paint()
    val paintCoord: Paint = Paint()
    val paintFunc: Paint = Paint()
    val paintText: Paint = Paint()

    var a: Translate = Translate(0F,1F,0F,1F,1000F,1000F)

    var koefx: Float? = -1F
    var koefy: Float? = -1F

    var ind: Int = -1

    var colorFunc: Int = 0xffff0000.toInt()
    var colorCoord: Int = 0xffff0000.toInt()

    val listOFFunc: List<String> =
        listOf("ln(x) - 5*cos(x)", "Math.pow(y, 2)/25-Math.pow(x, 2)/9 - 1")



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

            paintBG.color = 0x44ffe880
            paintCoord.color = colorCoord
            paintFunc.color = colorFunc
            paintText.textSize = 30F
            paintText.color = colorCoord


            drawPaint(paintBG)

                a.width = width.toFloat()
                a.height = height.toFloat()

                koefx = a.transformationX()
                koefy = a.transformationY()

                if (koefx != null && koefx != -1F) {
                    drawLine(0F, koefx!!, width.toFloat(), koefx!!, paintCoord)

                    drawStrokesOnAxe(
                        canvas,
                        koefx!!,
                        a.x1,
                        40,
                        a.x2,
                        width.toFloat(),
                        a.unX(),
                        true
                    )


                    if (ind == 1) {
                        commonFun(canvas, a, paintFunc, ::funk1)
                    } else {
                        commonFun(canvas, a, paintFunc, ::funk2firstPart)
                        commonFun(canvas, a, paintFunc, ::funk2secondPart)
                    }

                } else {
                    if (koefx == -1F) {
                        drawLine(
                            0F,
                            height.toFloat() / 2,
                            width.toFloat(),
                            height.toFloat() / 2,
                            paintCoord
                        )
                    }

                }


                if (koefy != null && koefy != -1F) {
                    drawLine(koefy!!, 0F, koefy!!, height.toFloat(), paintCoord)
                    drawStrokesOnAxe(
                        canvas,
                        koefy!!,
                        a.y1,
                        40,
                        a.y2,
                        height.toFloat(),
                        a.unY(),
                        false
                    )
                } else
                    if (koefy == -1F)
                        drawLine(
                            width.toFloat() / 2,
                            0F,
                            width.toFloat() / 2,
                            height.toFloat(),
                            paintCoord
                        )


            }

    }




    @SuppressLint("WrongCall")
    fun drawStrokesOnAxe(canvas: Canvas?, koef: Float, nach: Float, k: Int, konech: Float, size: Float, unit: Float, indikator: Boolean) {
        super.onDraw(canvas)
        canvas?.apply {

            var onTheAxe: Float
            if (indikator) {
                onTheAxe = nach
                drawLine(1F, koef - k, 1F, koef + k, paintText)
                drawText(onTheAxe.toInt().toString(), 1F + k / 4, koef + k * 2, paintText)
            } else {
                onTheAxe = konech
                drawLine(koef - k, 1F, koef + k, 1F, paintText)
                drawText(onTheAxe.toInt().toString(), koef + k * 2, 1F + k / 2, paintText)
            }

            var i = 1F
            var iter = 1

            while (i < size) {
                i += unit
                iter += 1

                if (iter == (konech - nach + 1).toInt()) {
                    if (indikator) {
                        onTheAxe += 1
                        drawLine(i - 5, koef - k, i - 5, koef + k, paintText)
                        drawText(onTheAxe.toInt().toString(), i - 15, koef + k * 2, paintText)

                    } else {
                        onTheAxe -= 1
                        drawLine(koef - k, i - 5, koef + k, i - 5, paintText)
                        drawText(onTheAxe.toInt().toString(), koef + k * 2, i - 15, paintText)
                    }

                } else {
                    if (indikator) {
                        onTheAxe += 1
                        drawLine(i, koef - k, i, koef + k, paintText)
                        drawText(onTheAxe.toInt().toString(), i - 7, koef + k * 2, paintText)
                    } else {
                        onTheAxe -= 1
                        drawLine(koef - k, i, koef + k, i, paintText)
                        drawText(onTheAxe.toInt().toString(), koef + k * 2, i - 7, paintText)
                    }
                }

            }

        }
    }


    fun funk1 (x:Float): Float{
        return (ln(x) - 5 * cos(x))
    }

    fun funk2firstPart (x:Float): Float{
        return (Math.pow(25 + 25 * Math.pow(x.toDouble(), 2.toDouble()) / 9, 0.5)).toFloat()
    }

    fun funk2secondPart (x:Float): Float{
        return (-Math.pow(25 + 25 * Math.pow(x.toDouble(), 2.toDouble()) / 9, 0.5)).toFloat()
    }

    @SuppressLint("WrongCall")
    fun commonFun(canvas: Canvas?, a:Translate, paint: Paint, f:(arg: Float)->Float){
        super.onDraw(canvas)
        canvas?.apply {

            var x1 = a.x1
            var y1 = f(x1)
            var x = a.toDecX((a.toScrX(a.x1)!! + 1))!!
            var y2 = f(x)
            var x2 = x
            if (a.toScrY(y1) != null && a.toScrX(x2) != null && a.toScrY(y2) != null) {
                drawLine(0F, a.toScrY(y1)!!, a.toScrX(x2)!!, a.toScrY(y2)!!, paint)
            }

            x1 = x2
            while (x < a.x2) {
                x = a.toDecX((a.toScrX(x)!! + 1))!!
                y2 = f(x)
                x2 = x
                if (a.toScrX(x1) != null && a.toScrY(y1) != null && a.toScrX(x2) != null && a.toScrY(y2) != null) {
                    drawLine(a.toScrX(x1)!!, a.toScrY(y1)!!, a.toScrX(x2)!!, a.toScrY(y2)!!, paint)
                }

                x1 = x2
                y1 = y2

            }
        }
    }



}



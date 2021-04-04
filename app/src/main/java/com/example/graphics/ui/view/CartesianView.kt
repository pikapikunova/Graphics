package com.example.graphics.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import com.example.graphics.R
import com.example.graphics.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.ln

class CartesianView(context : Context, attrs: AttributeSet?) : View(context, attrs) {
    constructor(context: Context) : this(context, null)

    val paintBG: Paint = Paint()
    val paintLine: Paint = Paint()
    val paintFunc: Paint = Paint()
    val paintText: Paint = Paint()

    var koefx: Float? = -1F
    var koefy: Float? = -1F

    var unitx: Float? = null
    var unity: Float? = null

    var xnach: Float? = null
    var xkonech: Float? = null
    var ynach: Float? = null
    var ykonech: Float? = null

    var ind: Int = -1

    var listOFFunc: List<String> =
        listOf("ln(x) - 5*cos(x)", "Math.pow(y, 2)/25-Math.pow(x, 2)/9 - 1")


    // val koefx = a.transformationX()
    //val koefy = a.transformationY()

    init {
        paintBG.color = 0xffffffd0.toInt()
        paintLine.color = 0xff0000ff.toInt()
        paintFunc.color = 0xff0000ff.toInt()
        paintText.color = 0xff0000ff.toInt()
        paintText.textSize = 30F
    }

    override fun setOnCreateContextMenuListener(l: OnCreateContextMenuListener?) {
        super.setOnCreateContextMenuListener(l)

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {

            drawPaint(paintBG)

            val a = Translate(
                xnach!!,
                xkonech!!,
                ynach!!,
                ykonech!!,
                width.toFloat(), height.toFloat()
            )

            koefx = a.transformationX()
            koefy = a.transformationY()

            unitx = a.unX()
            unity = a.unY()

            if (koefx != null && koefx != -1F) {
                drawLine(0F, koefx!!, width.toFloat(), koefx!!, paintLine)

                drawStrokesOnAxe(
                    canvas,
                    koefx!!,
                    xnach!!,
                    40,
                    xkonech!!,
                    width.toFloat(),
                    unitx!!,
                    true
                )


                if(ind == 1) {
                    commonFun(canvas, a, paintFunc, ::funk1)
                }
                else
                {
                    commonFun(canvas, a, paintFunc, ::funk2firstPart)
                    commonFun(canvas, a, paintFunc, ::funk2secondPart)
                }

            }
            else {
                if (koefx == -1F) {
                    drawLine(
                        0F,
                        height.toFloat() / 2,
                        width.toFloat(),
                        height.toFloat() / 2,
                        paintLine
                    )
                }

            }


            if (koefy != null && koefy != -1F) {
                drawLine(koefy!!, 0F, koefy!!, height.toFloat(), paintLine)
                drawStrokesOnAxe(canvas, koefy!!, ynach!!, 40, ykonech!!, height.toFloat(), unity!!, false)
            } else
                if (koefy == -1F)
                    drawLine(width.toFloat() / 2, 0F, width.toFloat() / 2, height.toFloat(), paintLine)


        }


    }

    @SuppressLint("WrongCall")
    fun drawStrokesOnAxe( canvas: Canvas?, koef: Float, nach: Float, k: Int, konech: Float, size: Float, unit: Float, indikator: Boolean) {
        super.onDraw(canvas)
        canvas?.apply {

            var onTheAxe = 1F
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
            var x = a.x1 + 0.1F
            var y2 = f(x)
            var x2 = x
            if (a.someY(y1) != null && a.someX(x2) != null && a.someY(y2) != null) {
                drawLine(0F, a.someY(y1)!!, a.someX(x2)!!, a.someY(y2)!!, paint)
            }

            x1 = x2
            while (x < a.x2) {
                x += 0.1F
                y2 = f(x)
                x2 = x
                if (a.someX(x1) != null && a.someY(y1) != null && a.someX(x2) != null && a.someY(y2) != null) {
                    drawLine(a.someX(x1)!!, a.someY(y1)!!, a.someX(x2)!!, a.someY(y2)!!, paint)
                }

                x1 = x2
                y1 = y2

            }
        }
    }
}



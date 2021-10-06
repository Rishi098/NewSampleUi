package com.itamazons.mychart

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.LineChartView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, PlaceholderFragment()).commit()
        }
    }


    class PlaceholderFragment : Fragment() {
        var chart: LineChartView? = null
        var data: LineChartData? = null
        var numberOfLines = 1
        val maxNumberOfLines = 4
        val numberOfPoints = 6
        var axis_Y_List: MutableList<AxisValue>? = null
        var axis_X_List: MutableList<AxisValue>? = null
        var randomNumbersTab = Array(maxNumberOfLines) { FloatArray(numberOfPoints) }
        var hasAxes = true
        var hasAxesNames = true
        var hasLines = true
        var hasPoints = true
        var shape = ValueShape.CIRCLE
        var isFilled = false
        var hasLabels = false
        var xinputlist: ArrayList<Float>? = null
        var yinputList: ArrayList<Float>? = null
        var isCubic = true
        var hasLabelForSelected = false
        var pointsHaveDifferentColor = false
        var hasGradientToTransparent = false
        var spinnervalue :String=""
        var volume:String=""

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            setHasOptionsMenu(true)
            val rootView: View = inflater.inflate(R.layout.fragment_line_chart, container, false)
            axis_Y_List = ArrayList()
            axis_Y_List!!.add(AxisValue(1.75f).setLabel("1.75"))
            axis_Y_List!!.add(AxisValue(1.77f).setLabel("1.77"))
            axis_Y_List!!.add(AxisValue(1.79f).setLabel("1.79"))
            axis_Y_List!!.add(AxisValue(1.81f).setLabel("1.81"))
            axis_Y_List!!.add(AxisValue(1.83f).setLabel("1.83"))
            axis_Y_List!!.add(AxisValue(1.85f).setLabel("1.85"))
            axis_Y_List!!.add(AxisValue(1.87f).setLabel("1.87"))
            axis_Y_List!!.add(AxisValue(1.91f).setLabel("1.91"))
            axis_Y_List!!.add(AxisValue(1.93f).setLabel("1.93"))


            axis_X_List = ArrayList()
            axis_X_List!!.add(AxisValue(6f).setLabel("6"))
            axis_X_List!!.add(AxisValue(7f).setLabel("7"))
            axis_X_List!!.add(AxisValue(8f).setLabel("8"))
            axis_X_List!!.add(AxisValue(9f).setLabel("9"))
            axis_X_List!!.add(AxisValue(10f).setLabel("10"))
            axis_X_List!!.add(AxisValue(11f).setLabel("11"))
            axis_X_List!!.add(AxisValue(12f).setLabel("12"))
            axis_X_List!!.add(AxisValue(13f).setLabel("13"))
            axis_X_List!!.add(AxisValue(14f).setLabel("14"))
            axis_X_List!!.add(AxisValue(15f).setLabel("15"))
            axis_X_List!!.add(AxisValue(16f).setLabel("16"))

            chart = rootView.findViewById(R.id.chart)
            chart!!.onValueTouchListener = ValueTouchListener()
            xinputlist = ArrayList()
            yinputList = ArrayList()
            xinputlist!!.add(7f)
            xinputlist!!.add(9f)
            xinputlist!!.add(10f)
            xinputlist!!.add(11f)
            xinputlist!!.add(13f)
            xinputlist!!.add(15f)


            yinputList!!.add(1.77f)
            yinputList!!.add(1.85f)
            yinputList!!.add(1.87f)
            yinputList!!.add(1.865f)
            yinputList!!.add(1.8265f)
            yinputList!!.add(1.77f)


            // Generate some random values.
            //generateValues()
            generateData()

            // Disable viewport recalculations, see toggleCubic() method for more info.
            chart!!.isViewportCalculationEnabled = true
            resetViewport()
            return rootView
        }

        // MENU


//        private fun generateValues() {
//            for (i in 0 until maxNumberOfLines) {
//                for (j in 0 until numberOfPoints) {
//                    randomNumbersTab[i][j] = Math.random().toFloat() * 6
//                }
//            }
//            System.out.println("RandomList" + randomNumbersTab)
//        }


        private fun resetViewport() {
            val v = Viewport(chart!!.maximumViewport)
            v.bottom = 1.75f
            v.top = 1.9f
            v.left = 6f
            v.right = 17f
            chart!!.maximumViewport = v
            chart!!.currentViewport = v
        }

        private fun generateData() {
            val lines: MutableList<Line> = ArrayList<Line>()
            val values: MutableList<PointValue> = ArrayList()
            for (i in 0 until numberOfPoints) {
                values.add(PointValue(xinputlist!!.get(i), yinputList!!.get(i)))
                val line = Line(values)
                line.setColor(R.color.black)
                line.setShape(shape)
                line.setCubic(isCubic)
                line.setFilled(isFilled)
                line.setStrokeWidth(2)
                line.pointRadius = 3
                line.setHasLabels(hasLabels)
                line.setHasLabelsOnlyForSelected(hasLabelForSelected)
                line.setHasLines(hasLines)
                line.setHasPoints(hasPoints)
//                line.setHasGradientToTransparent(hasGradientToTransparent)
                if (pointsHaveDifferentColor) {
                    line.setPointColor(ChartUtils.COLORS[(1) % ChartUtils.COLORS.size])
                }
                lines.add(line)
            }
            data = LineChartData(lines)
            if (hasAxes) {
                val axisX = Axis().setHasLines(true)
                axisX.lineColor=R.color.black
                val axisY: Axis = Axis().setHasLines(true)
                axisX.lineColor=R.color.black

                if (hasAxesNames) {
                    axisX.setName("Axis X")
                    axisY.setName("Axis Y")
                    axisX.textColor = R.color.black
                    axisY.textColor = R.color.black
                    axisX.isAutoGenerated = false
                    axisX.setValues(axis_X_List)
                    axisY.isAutoGenerated = false
                    axisY.setValues(axis_Y_List)

                }
                data!!.axisXBottom = axisX
                data!!.axisYLeft = axisY
            } else {
                data!!.axisXBottom = null
                data!!.axisYLeft = null
            }
            data!!.baseValue = Float.NEGATIVE_INFINITY
            chart!!.lineChartData = data
        }


        private fun toggleLabels() {
            hasLabels = !hasLabels
            if (hasLabels) {
                hasLabelForSelected = false
                chart!!.isValueSelectionEnabled = hasLabelForSelected
            }
            generateData()
        }

        private fun toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected
            chart!!.isValueSelectionEnabled = hasLabelForSelected
            if (hasLabelForSelected) {
                hasLabels = false
            }
            generateData()
        }


        private inner class ValueTouchListener : LineChartOnValueSelectListener {
            override fun onValueSelected(lineIndex: Int, pointIndex: Int, value: PointValue) {
                Toast.makeText(getActivity(), "Selected: $value", Toast.LENGTH_SHORT).show()
            }

            override fun onValueDeselected() {
            }
        }
    }
}
package com.itamazons.whatsdp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.LineChartView
import android.util.Log
import com.itamazons.whatsdp.utils.ErrorHandler
import com.itamazons.whatsdp.utils.AppConstants

class MainActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "MainActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, PlaceholderFragment())
                    .commit()
            }
        } catch (e: Exception) {
            ErrorHandler.handleError(this, TAG, "Error initializing fragment", e)
        }
    }

    class PlaceholderFragment : Fragment() {
        
        companion object {
            private const val TAG = "PlaceholderFragment"
        }
        
        private var chart: LineChartView? = null
        private var data: LineChartData? = null
        private var numberOfLines = 1
        private var axis_Y_List: MutableList<AxisValue>? = null
        private var axis_X_List: MutableList<AxisValue>? = null
        private var randomNumbersTab = Array(AppConstants.Chart.MAX_NUMBER_OF_LINES) { FloatArray(AppConstants.Chart.NUMBER_OF_POINTS) }
        private var hasAxes = true
        private var hasAxesNames = true
        private var hasLines = true
        private var hasPoints = true
        private var shape = ValueShape.CIRCLE
        private var isFilled = false
        private var hasLabels = false
        private var xinputlist: ArrayList<Float>? = null
        private var yinputList: ArrayList<Float>? = null
        private var isCubic = true
        private var hasLabelForSelected = false
        private var pointsHaveDifferentColor = false
        private var hasGradientToTransparent = false
        private var spinnervalue: String = ""
        private var volume: String = ""

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return try {
                setHasOptionsMenu(true)
                val rootView: View = inflater.inflate(R.layout.fragment_line_chart, container, false)
                initializeChartData()
                initializeChart(rootView)
                rootView
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error creating view", e)
                null
            }
        }

        private fun initializeChartData() {
            try {
                // Initialize Y-axis data
                axis_Y_List = mutableListOf(
                    AxisValue(1.75f).setLabel("1.75"),
                    AxisValue(1.77f).setLabel("1.77"),
                    AxisValue(1.79f).setLabel("1.79"),
                    AxisValue(1.81f).setLabel("1.81"),
                    AxisValue(1.83f).setLabel("1.83"),
                    AxisValue(1.85f).setLabel("1.85"),
                    AxisValue(1.87f).setLabel("1.87"),
                    AxisValue(1.91f).setLabel("1.91"),
                    AxisValue(1.93f).setLabel("1.93")
                )

                // Initialize X-axis data
                axis_X_List = mutableListOf(
                    AxisValue(6f).setLabel("6"),
                    AxisValue(7f).setLabel("7"),
                    AxisValue(8f).setLabel("8"),
                    AxisValue(9f).setLabel("9"),
                    AxisValue(10f).setLabel("10"),
                    AxisValue(11f).setLabel("11"),
                    AxisValue(12f).setLabel("12"),
                    AxisValue(13f).setLabel("13"),
                    AxisValue(14f).setLabel("14"),
                    AxisValue(15f).setLabel("15"),
                    AxisValue(16f).setLabel("16")
                )

                // Initialize input data
                xinputlist = arrayListOf(7f, 9f, 10f, 11f, 13f, 15f)
                yinputList = arrayListOf(1.77f, 1.85f, 1.87f, 1.865f, 1.8265f, 1.77f)
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error initializing chart data", e)
            }
        }

        private fun initializeChart(rootView: View) {
            try {
                chart = rootView.findViewById(R.id.chart)
                chart?.onValueTouchListener = ValueTouchListener()
                
                generateData()
                chart?.isViewportCalculationEnabled = true
                resetViewport()
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error initializing chart", e)
            }
        }

        private fun resetViewport() {
            try {
                val v = Viewport(chart?.maximumViewport ?: Viewport())
                v.bottom = AppConstants.Chart.DEFAULT_VIEWPORT_BOTTOM
                v.top = AppConstants.Chart.DEFAULT_VIEWPORT_TOP
                v.left = AppConstants.Chart.DEFAULT_VIEWPORT_LEFT
                v.right = AppConstants.Chart.DEFAULT_VIEWPORT_RIGHT
                chart?.maximumViewport = v
                chart?.currentViewport = v
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error resetting viewport", e)
            }
        }

        private fun generateData() {
            try {
                val lines: MutableList<Line> = mutableListOf()
                val values: MutableList<PointValue> = mutableListOf()
                
                for (i in 0 until AppConstants.Chart.NUMBER_OF_POINTS) {
                    val xValue = xinputlist?.getOrNull(i) ?: 0f
                    val yValue = yinputList?.getOrNull(i) ?: 0f
                    values.add(PointValue(xValue, yValue))
                }
                
                val line = Line(values).apply {
                    color = R.color.black
                    shape = this@PlaceholderFragment.shape
                    isCubic = this@PlaceholderFragment.isCubic
                    isFilled = this@PlaceholderFragment.isFilled
                    strokeWidth = AppConstants.Chart.DEFAULT_STROKE_WIDTH
                    pointRadius = AppConstants.Chart.DEFAULT_POINT_RADIUS
                    hasLabels = this@PlaceholderFragment.hasLabels
                    hasLabelsOnlyForSelected = this@PlaceholderFragment.hasLabelForSelected
                    hasLines = this@PlaceholderFragment.hasLines
                    hasPoints = this@PlaceholderFragment.hasPoints
                    
                    if (pointsHaveDifferentColor) {
                        pointColor = ChartUtils.COLORS[1 % ChartUtils.COLORS.size]
                    }
                }
                
                lines.add(line)
                data = LineChartData(lines)
                
                if (hasAxes) {
                    val axisX = Axis().apply {
                        hasLines = true
                        lineColor = R.color.black
                    }
                    val axisY = Axis().apply {
                        hasLines = true
                        lineColor = R.color.black
                    }

                    if (hasAxesNames) {
                        axisX.name = "Axis X"
                        axisY.name = "Axis Y"
                        axisX.textColor = R.color.black
                        axisY.textColor = R.color.black
                        axisX.isAutoGenerated = false
                        axisX.values = axis_X_List ?: mutableListOf()
                        axisY.isAutoGenerated = false
                        axisY.values = axis_Y_List ?: mutableListOf()
                    }
                    
                    data?.axisXBottom = axisX
                    data?.axisYLeft = axisY
                } else {
                    data?.axisXBottom = null
                    data?.axisYLeft = null
                }
                
                data?.baseValue = Float.NEGATIVE_INFINITY
                chart?.lineChartData = data
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error generating chart data", e)
            }
        }

        private fun toggleLabels() {
            try {
                hasLabels = !hasLabels
                if (hasLabels) {
                    hasLabelForSelected = false
                    chart?.isValueSelectionEnabled = hasLabelForSelected
                }
                generateData()
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error toggling labels", e)
            }
        }

        private fun toggleLabelForSelected() {
            try {
                hasLabelForSelected = !hasLabelForSelected
                chart?.isValueSelectionEnabled = hasLabelForSelected
                if (hasLabelForSelected) {
                    hasLabels = false
                }
                generateData()
            } catch (e: Exception) {
                ErrorHandler.handleError(context, TAG, "Error toggling label for selected", e)
            }
        }

        private inner class ValueTouchListener : LineChartOnValueSelectListener {
            override fun onValueSelected(lineIndex: Int, pointIndex: Int, value: PointValue) {
                try {
                    Toast.makeText(context, "Selected: $value", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    ErrorHandler.handleError(context, TAG, "Error showing selection toast", e)
                }
            }

            override fun onValueDeselected() {
                // Handle deselection if needed
            }
        }
    }
}
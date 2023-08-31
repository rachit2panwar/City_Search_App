//package com.example.calenderapplicationfrnd
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import androidx.annotation.Nullable
//import com.example.calenderapplicationfrnd.databinding.CalendarLayoutBinding
//import java.util.Calendar
//
//
//class CalendarView : LinearLayout {
//
//    private lateinit var binding: CalendarLayoutBinding
////
////    var header: LinearLayout? = null
////    var next: MaterialButton? = null
////    var prev: MaterialButton? = null
////    var filter: MaterialButton? = null
////    var monthAndYear: MaterialTextView? = null
////    var gridView: GridView? = null
//
//    //event handling
////    var eventHandler: EventHandler? = null
////    var events: ArrayList<EventDay>? = null
//
//    // current displayed month
//    private val currentDate: Calendar = Calendar.getInstance()
//
//    constructor(context: Context?) : super(context)
//    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
//        initControl(context, attrs)
//    }
//
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    ) {
//        initControl(context, attrs)
//    }
//
//    private fun initControl(context: Context, attrs: AttributeSet?) {
//        binding = CalendarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
////        LayoutInflater.from(context).inflate(R.layout.calendar_layout, this)
////        assignUiElements()
////        assignClickHandlers()
//    }
//
//    companion object {
//        // how many days to show, defaults to six weeks, 42 days
//        private const val DAYS_COUNT = 42
//
//        // default date format
//        private const val DATE_FORMAT = "MMM yyyy"
//    }
//}
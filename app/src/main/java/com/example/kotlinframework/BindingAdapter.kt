package com.example.kotlinframework

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kotlinframework.database.FrameworkData

@BindingAdapter("frameworkDataId")
fun FrameworkDataToId( textView: TextView, frameworkData: FrameworkData){
    val context = textView.context
    textView.text = frameworkData.id.toString()
}
@BindingAdapter("frameworkDataColumn")
fun TextView.bindFrameworkDataToColumn(frameworkData: FrameworkData){
    frameworkData.let {
        text = frameworkData.column
    }
}
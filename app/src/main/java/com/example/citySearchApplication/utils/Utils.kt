package com.example.citySearchApplication.utils


import android.view.*

inline fun View.debouncedOnClick(debounceTill: Long = 500, crossinline onClick: (v: View) -> Unit) {
    this.setOnClickListener(object : DebouncedOnClickListener(debounceTill) {
        override fun onDebouncedClick(v: View) {
            onClick(v)
        }
    })
}
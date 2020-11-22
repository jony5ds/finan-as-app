package com.jonatas.financask.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Calendar.formatarParaBrasileiro() : String
{
    val formatoData = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoData)
    return format.format(this.time)
}
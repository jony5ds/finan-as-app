package com.jonatas.financask.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private val formatoDeData = "dd/MM/yyyy"

@SuppressLint("SimpleDateFormat")
fun Calendar.formatarParaBrasileiro() : String
{
    val formatoData = formatoDeData
    val format = SimpleDateFormat(formatoData)
    return format.format(this.time)
}


@SuppressLint("SimpleDateFormat")
fun String.converterParaCalendar(): Calendar
{
    val formatoBrasileiro = SimpleDateFormat(formatoDeData)
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}
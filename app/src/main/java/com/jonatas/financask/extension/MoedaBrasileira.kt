package com.jonatas.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.moedaBrasileira() : String
{
    val formatoBr = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBr.format(this)
}
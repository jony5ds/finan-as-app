package com.jonatas.financask.model

import java.math.BigDecimal
import java.util.*

class Transacao constructor(val valor: BigDecimal,
                            val categoria: String = "Indefinida",
                            val tipo: Tipo,
                            val data: Calendar = Calendar.getInstance())

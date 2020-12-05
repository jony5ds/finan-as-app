package com.jonatas.financask.ui.activity

import com.jonatas.financask.model.Tipo
import com.jonatas.financask.model.Transacao
import java.math.BigDecimal

class ResumoPresenter( private val transacoes : List<Transacao>) {

    val calcularTotalReceita get() = somaPor(Tipo.RECEITA)

    val calcularTotalDespesa get() = somaPor(Tipo.DESPESA)

    val calcularDiferenca get() = calcularTotalReceita.subtract(calcularTotalDespesa)

    private fun somaPor(tipo : Tipo): BigDecimal {
        return BigDecimal( transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() })
    }

}
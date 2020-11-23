package com.jonatas.financask.ui.activity

import com.jonatas.financask.extension.moedaBrasileira
import com.jonatas.financask.model.Tipo
import com.jonatas.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoPresenter( private val transacoes : List<Transacao>) {

    fun calcularTotalReceita(): BigDecimal {
        var totalReceita = BigDecimal.ZERO

        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        return totalReceita
    }

    fun calcularTotalDespesa(): BigDecimal {
        var totalDespesa = BigDecimal.ZERO

        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        return totalDespesa
    }

    fun calcularDiferenca(): BigDecimal {
        return calcularTotalReceita().subtract(calcularTotalDespesa())
    }
}
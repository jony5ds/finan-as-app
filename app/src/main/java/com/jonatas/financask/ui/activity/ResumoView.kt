package com.jonatas.financask.ui.activity

import android.view.View
import com.jonatas.financask.extension.moedaBrasileira
import com.jonatas.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(
    private val view: View,
    transacoes: List<Transacao>
) {

    val presenter = ResumoPresenter(transacoes)

    fun exibirTotalReceitaNoResumo() {
        val totalReceita = presenter.calcularTotalReceita()
        view.resumo_card_receita.text = totalReceita.moedaBrasileira()
    }

    fun exibirTotalDespesaNoResumo() {
        val totalDespesa = presenter.calcularTotalDespesa()
        view.resumo_card_despesa.text = totalDespesa.moedaBrasileira()
    }

    fun exibirTotal() {
        val total = presenter.calcularDiferenca()
        view.resumo_card_total.text = total.moedaBrasileira()
    }
}
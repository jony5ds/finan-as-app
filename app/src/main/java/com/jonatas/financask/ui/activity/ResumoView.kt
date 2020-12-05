package com.jonatas.financask.ui.activity

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import com.jonatas.financask.R
import com.jonatas.financask.extension.moedaBrasileira
import com.jonatas.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val view: View,
    transacoes: List<Transacao>
) {

    val presenter = ResumoPresenter(transacoes)

    private val corReceita = ContextCompat.getColor(view.context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(view.context, R.color.despesa)

    @SuppressLint("ResourceAsColor")
    fun exibirTotalReceitaNoResumo() {
        val totalReceita = presenter.calcularTotalReceita
        with(view.resumo_card_receita) {
            text = totalReceita.moedaBrasileira()
            resumo_card_receita.setTextColor(corReceita)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun exibirTotalDespesaNoResumo() {
        val totalDespesa = presenter.calcularTotalDespesa
        with(view.resumo_card_despesa){
            text = totalDespesa.moedaBrasileira()
            setTextColor(corDespesa)
        }

    }

    fun  exibirTotal() {
        val total = presenter.calcularDiferenca
        with(view.resumo_card_total){

            text = total.moedaBrasileira()

            if (total < BigDecimal.ZERO)
            {
                setTextColor(corDespesa)
            }
            else
            {
                setTextColor(corReceita)
            }

        }
    }

    fun atualiza(){
        exibirTotalReceitaNoResumo()
        exibirTotalDespesaNoResumo()
        exibirTotal()
    }
}
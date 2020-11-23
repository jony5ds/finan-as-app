package com.jonatas.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonatas.financask.R
import com.jonatas.financask.model.Transacao
import com.jonatas.financask.model.Tipo
import com.jonatas.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes = criarLista()
        PopularLista(transacoes)
        ExibirResumo(transacoes)

    }

    private fun ExibirResumo(transacoes: List<Transacao>) {
        val resumoView = ResumoView(window.decorView, transacoes)
        resumoView.exibirTotalReceitaNoResumo()
        resumoView.exibirTotalDespesaNoResumo()
        resumoView.exibirTotal()
    }


    private fun PopularLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun criarLista(): List<Transacao> {
        val transacoes = listOf (
            Transacao (
                valor = BigDecimal(250.00),
                categoria = "Almoço de final de semana",
                tipo = Tipo.DESPESA,
                data = Calendar.getInstance()
            ),
            Transacao (
                valor = BigDecimal(600),
                categoria = "Economia",
                tipo = Tipo.RECEITA
            ),
            Transacao (
                valor = BigDecimal(400),
                tipo = Tipo.RECEITA
            ),
            Transacao (
                valor = BigDecimal(100),
                categoria = "Prêmio",
                tipo = Tipo.DESPESA
            )
        )
        return transacoes
    }
}
package com.jonatas.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jonatas.financask.R
import com.jonatas.financask.delegate.TransacaoDelegate
import com.jonatas.financask.extension.formatarParaBrasileiro
import com.jonatas.financask.model.Tipo
import com.jonatas.financask.model.Transacao
import com.jonatas.financask.ui.adapter.ListaTransacoesAdapter
import com.jonatas.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes = mutableListOf<Transacao>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        PopularLista()

        ExibirResumo()

        lista_transacoes_adiciona_receita
            .setOnClickListener {
                AdicionaTransacaoDialog(
                    window.decorView as ViewGroup,
                    this
                )
                    .configuraDialog(object : TransacaoDelegate {
                        override fun delegate(transacao: Transacao) {
                            atualizaTransacao(transacao)
                            lista_transacoes_adiciona_menu.close(true)
                        }

                    })
            }
    }

    private fun atualizaTransacao(transacaoCriada: Transacao) {
        transacoes.add(transacaoCriada)
        PopularLista()
        ExibirResumo()
    }

    private fun ExibirResumo() {
        val resumoView = ResumoView(window.decorView, transacoes)
        resumoView.exibirTotalReceitaNoResumo()
        resumoView.exibirTotalDespesaNoResumo()
        resumoView.exibirTotal()
    }

    private fun PopularLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}


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
import com.jonatas.financask.extension.formatarParaBrasileiro
import com.jonatas.financask.model.Tipo
import com.jonatas.financask.model.Transacao
import com.jonatas.financask.ui.adapter.ListaTransacoesAdapter
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
                val view: View = window.decorView
                val viewCriada = LayoutInflater.from(this)
                    .inflate(
                        R.layout.form_transacao,
                        view as ViewGroup,
                        false
                    )

                val ano = 2017
                val mes = 9
                val dia = 18

                val hoje = Calendar.getInstance()
                viewCriada.form_transacao_data
                    .setText(hoje.formatarParaBrasileiro())
                viewCriada.form_transacao_data
                    .setOnClickListener {
                        DatePickerDialog(
                            this,
                            { view, ano, mes, dia ->
                                val dataSelecionada = Calendar.getInstance()
                                dataSelecionada.set(ano, mes, dia)
                                viewCriada.form_transacao_data
                                    .setText(dataSelecionada.formatarParaBrasileiro())
                            }, ano, mes, dia
                        )
                            .show()
                    }


                val adapter = ArrayAdapter
                    .createFromResource(
                        this,
                        R.array.categorias_de_receita,
                        android.R.layout.simple_spinner_dropdown_item
                    )

                viewCriada.form_transacao_categoria.adapter = adapter

                AlertDialog.Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewCriada)
                    .setPositiveButton(
                        "Adicionar"
                    ) { dialogInterface, i ->
                        val valorEmTexto = viewCriada
                            .form_transacao_valor.text.toString()
                        val dataEmTexto = viewCriada
                            .form_transacao_data.text.toString()
                        val categoriaEmTexto = viewCriada
                            .form_transacao_categoria.selectedItem.toString()

                        var valor = try
                        {
                            BigDecimal(valorEmTexto)
                        } catch (exception: NumberFormatException)
                        {
                            Toast.makeText(
                                this,
                                "Falha na conversão de valor",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            BigDecimal.ZERO
                        }

                        val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
                        val dataConvertida: Date = formatoBrasileiro.parse(dataEmTexto)
                        val data = Calendar.getInstance()
                        data.time = dataConvertida

                        val transacaoCriada = Transacao(
                            tipo = Tipo.RECEITA,
                            valor = valor,
                            data = data,
                            categoria = categoriaEmTexto
                        )
                        atualizaTransacao(transacaoCriada)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
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


package com.jonatas.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jonatas.financask.R
import com.jonatas.financask.delegate.TransacaoDelegate
import com.jonatas.financask.extension.converterParaCalendar
import com.jonatas.financask.extension.formatarParaBrasileiro
import com.jonatas.financask.model.Tipo
import com.jonatas.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {

    private val viewCriada = criarLayout()

    public fun configuraDialog(transacaoDelegate: TransacaoDelegate) {

        configurarCampoData()

        configurarCampoCategoria()

        configurarFormulario(transacaoDelegate)
    }

    private fun configurarFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCriada)
            .setPositiveButton(
                "Adicionar"
            ) { _, _ ->
                val valorEmTexto = viewCriada
                    .form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada
                    .form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada
                    .form_transacao_categoria.selectedItem.toString()

                val valor = converterValor(valorEmTexto)

                val data = dataEmTexto.converterParaCalendar()

                val transacaoCriada = Transacao(
                    tipo = Tipo.RECEITA,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                transacaoDelegate.delegate(transacaoCriada)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun converterValor(valorEmTexto: String) : BigDecimal {
        return  try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            mensagemErro()
            BigDecimal.ZERO
        }
    }

    private fun mensagemErro() {
        Toast.makeText(
            context,
            "Falha na conversão de valor",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun configurarCampoCategoria() {
        val adapter = ArrayAdapter
            .createFromResource(
                context,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item
            )

        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configurarCampoData() {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data
            .setText(hoje.formatarParaBrasileiro())

        viewCriada.form_transacao_data
            .setOnClickListener {
                DatePickerDialog(
                    context,
                    { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        viewCriada.form_transacao_data
                            .setText(dataSelecionada.formatarParaBrasileiro())
                    }, ano, mes, dia
                )
                    .show()
            }
    }

    private fun criarLayout(): View {
        return LayoutInflater.from(context)
            .inflate(
                R.layout.form_transacao,
                viewGroup,
                false
            )

    }
}
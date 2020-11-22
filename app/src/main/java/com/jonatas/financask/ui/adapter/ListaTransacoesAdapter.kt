package com.jonatas.financask.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.jonatas.financask.R
import com.jonatas.financask.extension.abreviar
import com.jonatas.financask.extension.formatarParaBrasileiro
import com.jonatas.financask.extension.moedaBrasileira
import com.jonatas.financask.model.Transacao
import com.jonatas.financask.model.Tipo
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val mTransacoes: List<Transacao>,
                             private val mContext: Context) : BaseAdapter() {

    private val LIMITE_CARACTER = 14

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(mContext)
            .inflate(R.layout.transacao_item, parent, false)

        val transacao = mTransacoes[position]

        adicionarNome(inflate, transacao)
        adicionarValor(inflate, transacao)

        if (transacao.tipo == Tipo.DESPESA)
        {
            configurarItemParaSerDespesa(inflate)
        }
        else
        {
            configurarItemParaSerReceita(inflate)
        }

        adicionarData(inflate, transacao)

        return inflate
    }

    private fun adicionarValor(
        inflate: View,
        transacao: Transacao
    ) {
        inflate.transacao_valor.text = transacao.valor.moedaBrasileira()
    }

    private fun adicionarNome(
        inflate: View,
        transacao: Transacao
    ) {
        inflate.transacao_categoria.text = transacao.categoria.abreviar(LIMITE_CARACTER)
    }

    private fun adicionarData(
        inflate: View,
        transacao: Transacao
    ) {
        inflate.transacao_data.text = transacao.data.formatarParaBrasileiro()
    }

    private fun configurarItemParaSerReceita(inflate: View)
    {
        inflate.transacao_valor.setTextColor(ContextCompat.getColor(mContext, R.color.receita))
        inflate.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
    }

    private fun configurarItemParaSerDespesa(inflate: View)
    {
        inflate.transacao_valor.setTextColor(ContextCompat.getColor(mContext, R.color.despesa))
        inflate.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
    }

    override fun getItem(position: Int): Transacao {
        return mTransacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return mTransacoes.size
    }
}
package com.jonatas.financask.delegate

import com.jonatas.financask.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}
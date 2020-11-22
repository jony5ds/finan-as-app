package com.jonatas.financask.extension

private val LIMITE_CARACTER = 14

fun String.abreviar(caracteres : Int) : String
{
    if(this.length > LIMITE_CARACTER)
    {
        val primeiraCaracter = 0
        return "${this.substring(primeiraCaracter, caracteres)}..."
    }
    return this
}
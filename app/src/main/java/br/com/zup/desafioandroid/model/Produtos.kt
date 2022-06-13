package br.com.zup.desafioandroid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Produtos(
    private var nomeProdutos: String,
    private var quantidadeProdutos: Int,
    private var valorProduto: Double,
    private var Receita: String
) : Parcelable {
    fun getNomeProduto() = nomeProdutos
    fun getQuantidadeProduto() = quantidadeProdutos
    fun getValorProduto() = valorProduto
    fun getReceita()= Receita
}
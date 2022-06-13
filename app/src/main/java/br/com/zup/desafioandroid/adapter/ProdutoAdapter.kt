package br.com.zup.desafioandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.desafioandroid.databinding.ProdutoItemBinding
import br.com.zup.desafioandroid.model.Produtos

class ProdutoAdapter(
    private var listaDeProduto: MutableList<Produtos>,
    private val clickProduto: (produto: Produtos) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun exibirInformacoesView(produto: Produtos) {
            binding.informacao.text = produto.getQuantidadeProduto().toString() + " - "+ produto.getNomeProduto()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ProdutoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produtos = listaDeProduto[position]
        holder.exibirInformacoesView(produtos)
        holder.binding.cvItemProdutos.setOnClickListener {
            clickProduto(produtos)
        }
    }
    override fun getItemCount(): Int = listaDeProduto.size

    fun atualizarProduto(novaListaProduto: MutableList<Produtos>) {
        if (listaDeProduto.size == 0) {
            listaDeProduto.addAll(novaListaProduto)
        }

        notifyDataSetChanged()
    }
}
package br.com.zup.desafioandroid.produto.Fragment.Detalhe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.zup.desafioandroid.KEY_PRODUTOS
import br.com.zup.desafioandroid.R
import br.com.zup.desafioandroid.databinding.FragmentDetalheBinding
import br.com.zup.desafioandroid.model.Produtos
import br.com.zup.desafioandroid.produto.ProdutosActivity


class DetalheFragment : Fragment() {
    private lateinit var binding: FragmentDetalheBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ProdutosActivity).supportActionBar?.title = getString(R.string.produto_detalhe)
        recuperarExibirDadosProduto()
        binding.ivFavorito.setOnClickListener{
            toastMensagemDoBotaoFavorito()
        }
    }

    private fun recuperarExibirDadosProduto() {
        val produto = arguments?.getParcelable<Produtos>(KEY_PRODUTOS)
        if (produto != null){
            binding.tvNomeProduto.text = produto.getNomeProduto()
            binding.tvQtd.text ="Quantidade: "+ produto.getQuantidadeProduto().toString()
            binding.tvValor.text ="Valor unitário: "+ produto.getValorProduto().toString()
            binding.tvReceita.text = "Receita: "+ produto.getReceita()
        }
    }
    private fun toastMensagemDoBotaoFavorito() {
        Toast.makeText(context, "Produto favoritado!", Toast.LENGTH_SHORT).show()
    }
}
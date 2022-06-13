package br.com.zup.desafioandroid.produto.Fragment.ValorTotal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.desafioandroid.KEY_PRODUTOS
import br.com.zup.desafioandroid.R
import br.com.zup.desafioandroid.databinding.FragmentValorTotalBinding
import br.com.zup.desafioandroid.model.Produtos
import br.com.zup.desafioandroid.produto.ProdutosActivity


class ValorTotalFragment : Fragment() {
    private lateinit var binding: FragmentValorTotalBinding
    private var listaProduto = mutableListOf<Produtos>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentValorTotalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ProdutosActivity).supportActionBar?.title = getString(R.string.appbar_total)
        somaDoValores()

        binding.bAdicionarProduto.setOnClickListener {
            botaoAdiciona()
        }
        botaoVerProduto()

    }

    private fun botaoAdiciona() {

        NavHostFragment.findNavController(this).navigate(
            R.id.action_valorTotalFragment_to_produtosFragment,
            bundleOf(KEY_PRODUTOS to listaProduto)
        )
    }

    @SuppressLint("SetTextI18n")
    private fun somaDoValores() {
        var somaTotal = 0.0
        listaProduto = arguments?.getParcelableArrayList(KEY_PRODUTOS)!!
        listaProduto.forEach {
            somaTotal += it.getQuantidadeProduto() * it.getValorProduto()
        }

        binding.tvTotal.text = "Valor total do produtos é de R$ $somaTotal"

    }

    private fun botaoVerProduto() {
        binding.bVerProduto.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_valorTotalFragment_to_verProdutosFragment,
                bundleOf(KEY_PRODUTOS to listaProduto)
            )
        }
    }

}
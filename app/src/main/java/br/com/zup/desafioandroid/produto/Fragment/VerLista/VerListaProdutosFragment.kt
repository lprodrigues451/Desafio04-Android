package br.com.zup.desafioandroid.produto.Fragment.VerLista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.desafioandroid.KEY_PRODUTOS
import br.com.zup.desafioandroid.R
import br.com.zup.desafioandroid.adapter.ProdutoAdapter
import br.com.zup.desafioandroid.databinding.FragmentVerProdutosBinding
import br.com.zup.desafioandroid.model.Produtos
import br.com.zup.desafioandroid.produto.ProdutosActivity

class VerListaProdutosFragment : Fragment() {
    private lateinit var binding: FragmentVerProdutosBinding

    private val produtosAdapter: ProdutoAdapter by lazy {
        ProdutoAdapter(arrayListOf(), this::irParaVerProdutos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ProdutosActivity).supportActionBar?.title = getString(R.string.produto_name)
        atualizarProdutos()
    }

    private fun irParaVerProdutos(produtos: Produtos) {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_verProdutosFragment_to_detalheFragment,
            bundleOf(KEY_PRODUTOS to produtos)
        )
    }

    private fun atualizarProdutos() {
        val listaProdutos = arguments?.getParcelableArrayList<Produtos>(KEY_PRODUTOS)
        if (listaProdutos != null) {
            produtosAdapter.atualizarProduto(listaProdutos)
            exibirRecyclerView()
        }
    }

    private fun exibirRecyclerView() {
        binding.rvListaProduto.adapter = produtosAdapter
        binding.rvListaProduto.layoutManager = LinearLayoutManager(context)

    }
}
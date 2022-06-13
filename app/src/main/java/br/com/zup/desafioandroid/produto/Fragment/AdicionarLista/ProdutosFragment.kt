package br.com.zup.desafioandroid.produto.Fragment.AdicionarLista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.desafioandroid.KEY_PRODUTOS
import br.com.zup.desafioandroid.R
import br.com.zup.desafioandroid.databinding.FragmentProdutosBinding
import br.com.zup.desafioandroid.model.Produtos
import br.com.zup.desafioandroid.produto.ProdutosActivity

class ProdutosFragment : Fragment() {
    private lateinit var binding: FragmentProdutosBinding
    private var listaProduto = mutableListOf<Produtos>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ProdutosActivity).supportActionBar?.title = getString(R.string.produto_name)
        botaoAdicionar()
        botaoVerlista()
        botaoValorTotal()
        VerListaDoValorTotal()
    }

    private fun recuperarDados(): Produtos? {
        val nomeProduto: String = binding.etNomeProduto.text.toString()
        val quantidadeProdutos: String = binding.etQuantidadeProduto.text.toString()
        val valorProdutos: String = binding.etValorProduto.text.toString()
        val receitaProdutos: String = binding.etReceitaProduto.text.toString()

        if (nomeProduto.isNotEmpty()
            && quantidadeProdutos.isNotEmpty()
            && valorProdutos.isNotEmpty()
            && receitaProdutos.isNotEmpty()
        ) {

            limparCampoEdicao()
            return Produtos(
                nomeProduto,
                quantidadeProdutos.toInt(),
                valorProdutos.toDouble(),
                receitaProdutos
            )
        }
        return null
    }

    private fun adicionarItemListaProduto() {
        val produto = recuperarDados()
        if (produto != null) {
            listaProduto.add(produto)
            toastMensagemDoBotaoCadastro()
        } else {
            exibirMensagemErro()
        }
    }

    private fun VerListaDoValorTotal(){
        val lista = arguments?.getParcelableArrayList<Produtos>(KEY_PRODUTOS)
        lista?.let {
            listaProduto = it }
    }


    private fun botaoAdicionar() {
        binding.bAdicionarProduto.setOnClickListener {
            adicionarItemListaProduto()
        }
    }

    private fun botaoVerlista() {
        binding.bVerProduto.setOnClickListener {
            if (listaProduto.size != 0) {
                NavHostFragment.findNavController(this).navigate(
                    R.id.action_produtosFragment_to_verProdutosFragment,
                    bundleOf(KEY_PRODUTOS to listaProduto)
                )
            } else {
                toastMensagemNãoContem()
            }
        }
    }

    private fun botaoValorTotal() {
        binding.bValorTotal.setOnClickListener {
            if (listaProduto.size != 0) {
                NavHostFragment.findNavController(this).navigate(
                    R.id.action_produtosFragment_to_valorTotalFragment,
                    bundleOf(KEY_PRODUTOS to listaProduto)
                )
            } else {
                toastMensagemNãoContem()
            }
        }
    }

    private fun toastMensagemDoBotaoCadastro() {
        Toast.makeText(context, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun toastMensagemNãoContem() {
        Toast.makeText(context, "Não contém nenhum produto!", Toast.LENGTH_SHORT).show()
    }

    private fun exibirMensagemErro() {
        binding.etNomeProduto.error = "Por favor preencha o campo de nome"
        binding.etQuantidadeProduto.error = "Por favor preencha o campo de detalhe"
        binding.etValorProduto.error = "Por favor preencha o campo de detalhe"
        binding.etReceitaProduto.error = "Por favor preencha o campo de detalhe"

    }

    private fun limparCampoEdicao() {
        binding.etNomeProduto.text.clear()
        binding.etQuantidadeProduto.text.clear()
        binding.etValorProduto.text.clear()
        binding.etReceitaProduto.text.clear()
    }
}
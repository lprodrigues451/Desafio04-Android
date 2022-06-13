package br.com.zup.desafioandroid.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.desafioandroid.R
import br.com.zup.desafioandroid.databinding.ActivityMainBinding
import br.com.zup.desafioandroid.produto.ProdutosActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exibirNaAppBar()

        binding.bvIrProdutos.setOnClickListener {
            startActivity(Intent(this, ProdutosActivity::class.java))
        }
    }

    private fun exibirNaAppBar() {
        supportActionBar?.setTitle(R.string.SimCity_SaoJoao_name)
    }

}
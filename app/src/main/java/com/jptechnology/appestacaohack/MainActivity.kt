package com.jptechnology.appestacaohack

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Get email passed via intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Access Shared Preferences file
        val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        // Recover data from Shared Preferences file
        val nome = sharedPrefs.getString("NOME", "Chave não encontrada")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "Chave não encontrada")
        val genero = sharedPrefs.getString("GENERO", "Chave não encontrada")

        // Show revered data on screen
        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = email
        txvMainGenero.text = genero

        // Listen to the exit button
        btnMainSair.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja mesmo sair?")
                .setPositiveButton("Sair"){_, _ ->
                    val mIntent = Intent(this, LoginActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNegativeButton("Cancelar"){_ ,_ ->}
                .setCancelable(false)
                .create()
                .show()
        }
        // Listen to site cellep button
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }
    }
}
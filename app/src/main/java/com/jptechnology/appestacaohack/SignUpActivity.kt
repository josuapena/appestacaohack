package com.jptechnology.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sing_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        // Option list for spinner
        val listaGenero = arrayListOf<String>("Selecione o gênero", "Feminino", "Masculino", "Não-binário")
        // Adapter for spinner
        val generoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaGenero
        )
        // Plug the adapter to the spinner
        spnCadastroGenero.adapter = generoAdapter

        // Listening to sign up button
        btnCadastroCadastrar.setOnClickListener {
            // Getting data from form
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            // Field validation
            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                // All fields are filled

                // Create or access the sharedPreferences file
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // File editing
                val editPrefs = sharedPrefs.edit()

                // Preparing data to be saved
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                // Save data into Shared Preferences file
                editPrefs.apply()

                // Success message to user

                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show()
                // Open main activity
                val mIntent = Intent(this, MainActivity::class.java)

                // Passing info through intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity((mIntent))

                // Close all screens
                finishAffinity()
            }
        }
    }
}
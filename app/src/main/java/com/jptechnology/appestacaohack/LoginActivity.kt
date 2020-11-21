package com.jptechnology.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Listing to the login button click
         btnLoginEntrar.setOnClickListener {
            // Get input data
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()
            val mensagem = "Usuário logado com sucesso"
            val dadosErrados = "Usuário ou senha errados, tente novamente."
            // Field validation

            if (email.isEmpty()){
                // Error message
                edtLoginEmail.error = "Campo obrigatório!"
                edtLoginEmail.requestFocus()
            }else if(senha.isEmpty()){
                edtLoginSenha.error = "Campo obrigatório!"
                edtLoginSenha.requestFocus()
            }else{
                // Data recovery
                // Access Shared Preferences file
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Recover data from Shared Preferences file
                val emailCadastro = sharedPrefs.getString("EMAIL", "")
                val senhaCadastro = sharedPrefs.getString("SENHA", "")
                //Field verification
                if(email == emailCadastro && senha == senhaCadastro){
                    // Success message through Toast widget
                    Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                    // Open MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                }else{
                    Toast.makeText(this, dadosErrados, Toast.LENGTH_LONG).show()
                }
            }
        }
        // Listening sign up button
        btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, SignUpActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }
}
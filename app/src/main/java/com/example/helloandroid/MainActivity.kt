package com.example.helloandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.helloandroid.domain.LoginService
import com.example.helloandroid.extension.alert

//Comentário de alteração 1.1
class MainActivity : LogActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null) {
            // recupera o count
            Log.d("ciclo_vida","Recuperando estado")
            count = savedInstanceState.getInt("count")

        }
        Log.d("ciclo_vida","Count: $count")

        findViewById<Button>(R.id.btLogin).setOnClickListener {
            // Delega o tratamento para o método correto
            onClickLogin()
        }
        findViewById<TextView>(R.id.btEsqueciSenha).setOnClickListener {
            onClickEsqueciSenha()
        }
        findViewById<TextView>(R.id.btCadastrar).setOnClickListener {
            onClickCadastrar()
        }

        findViewById<TextView>(R.id.btLocalizacao).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"))
            startActivity(intent)
        }
        findViewById<TextView>(R.id.btFaleConosco).setOnClickListener {
            onClickContato()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ciclo_vida","Salvando estado")
        count++
        outState.putInt("count",count)
    }


    // Um método para cada evento aqui
    private fun onClickLogin() {
        // Encontra as views
        val tLogin = findViewById<TextView>(R.id.tLogin)
        val tSenha = findViewById<TextView>(R.id.tSenha)
        // Lê os textos
        val login = tLogin.text.toString()
        val senha = tSenha.text.toString()
        //Log.d("[AULA4-Prog]","Login: $login, senha: $senha")
        val service = LoginService()
        val user = service.login(login,senha)
        if(user != null) {
            // Login OK, vai para a Home
                // startActivity(Intent(this,HomeActivity::class.java))
            finish()
            val intent = Intent(this,HomeActivity::class.java)
            val args = Bundle()
            args.putSerializable("usuario", user)
            intent.putExtras(args)
            startActivity(intent)

        } else {
            alert("Login incorreto, digite os dados novamente")
        }
    }
    private fun onClickEsqueciSenha() {
        startActivity(Intent(this,EsqueciSenhaActivity::class.java))
    }
    private fun onClickCadastrar() {
        startActivity(Intent(this,CadastroActivity::class.java))
    }
    private fun onClickContato() {
        startActivity(Intent(this, ContatoActivity::class.java))
    }
}
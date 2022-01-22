package com.example.helloandroid.domain

import android.util.Log

class CadastroService {
    fun cadastrar(model: CadastroModel): Boolean {
        Log.d("[AULA4-Prog]","Cadastro $model")
        return true
    }
}
package com.example.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
/* Activity que imprime logs nos métodos de ciclo de vida */

open class LogActivity : AppCompatActivity() {
    private val TAG = "ciclo_vida"
    // Nome da classe sem o pacote
    private val className: String
        get() {
            val s = javaClass.name
            return s.substring(s.lastIndexOf(".")+1)
        }
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        Log.d(TAG, className + ".onCreate().")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, className + ".onStart().")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, className + ".onRestart().")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, className + ".onResume().")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, className + ".onPause().")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, className + ".onSaveInstanceState().")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, className + ".onStop().")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, className + ".onDestroy().")
    }
}
package com.example.helloandroid

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import com.example.helloandroid.domain.Usuario
import java.io.File

class HomeActivity :  LogActivity()  {
    var file: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val args = intent.extras
        val user = args?.getSerializable("usuario") as Usuario
        findViewById<TextView>(R.id.tNome).text = "Olá, ${user.nome} - seu e-mail é: ${user.email}"

        findViewById<ImageButton>(R.id.btAbrirCamera).setOnClickListener {
            onClickCamera()
        }
        if (savedInstanceState != null) {
            // (*2*) Se girou a tela recupera o estado
            file = savedInstanceState.getSerializable("file") as File
            showImage(file)
        }
        // Valida a permissão para Câmera
        val ok = PermissionUtils.request(this, arrayOf(Manifest.permission.CAMERA))
        if(!ok) {
            // Deixa o botão Invisível
            findViewById<ImageButton>(R.id.btAbrirCamera).visibility = View.INVISIBLE
        }
    }
    // Cria um arquivo no sdcard privado do aplicativo
    // A câmera vai salvar a foto no caminho deste arquivo
    fun createFile(fileName: String): File {
        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (sdCardDir != null && sdCardDir.exists()) {
            sdCardDir.mkdir()
        }
        val file = File(sdCardDir, fileName)
        // Salva file como atributo para salvar estado caso gire a tela
        this.file = file
        return file
    }
    private fun onClickCamera() {
        // (*1*) Cria o caminho do arquivo no sdcard
        val file = createFile("foto.jpg")
        log("Camera file: $file")
        // Chama a intent informando o arquivo para salvar a foto
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider", file
        )
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        // Chama a intent de captura de foto
        startActivityForResult(i, 0)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // (*4*) Se a câmera retornou, vamos mostrar o arquivo da foto
            showImage(file)
        }
    }
    // Atualiza a imagem na tela
    private fun showImage(file: File?) {
        if (file != null && file.exists()) {
            // (*5*) Redimensiona a imagem para o tamanho do ImageView
            val bitmap = ImageUtils.resize(file, 1200, 800)
            log("img1: w/h:" +  findViewById<ImageView>(R.id.imgFoto).width + "/" +  findViewById<ImageView>(R.id.imgFoto).height)
            log("img2: w/h:" + bitmap.width + "/" + bitmap.height)
            log("file:" + file)
            // (*6*) Mostra a foto no ImageView
            findViewById<ImageView>(R.id.imgFoto).setImageBitmap(bitmap)
        }
    }
    fun log(s: String) {
        Log.d("android", s)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // (*3*) Salvar o estado caso gire a tela
        outState.putSerializable("file", file)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val ok = PermissionUtils.validate(this, permissions)
        if(ok) {
            // Deixa o botão visível se o usuário aceitou a permissão
            findViewById<ImageButton>(R.id.btAbrirCamera).visibility = View.VISIBLE
        }
    }
}
package com.example.helloandroid

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    // Solicita uma permissão se ainda não foi concedida
    fun request(activity: Activity, permissions: Array<String>) : Boolean {
        val ok = validate(activity, permissions)
        if (!ok) {
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                1
            )
        }
        return ok
    }
    // Valida se as permissões foram concedidas
    fun validate(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(context, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                Log.d("Ciclo_vida", "Não deu permissão!")
                return false
            }
        }
        return true
    }
}
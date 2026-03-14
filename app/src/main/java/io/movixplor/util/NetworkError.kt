package io.movixplor.util

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkError {
    fun parse(e: Exception): String {
        return when (e) {
            is UnknownHostException ->
                "Sin conexión a internet. Comprueba tu red."
            is SocketTimeoutException ->
                "La conexión tardó demasiado. Inténtalo de nuevo."
            is HttpException -> when (e.code()) {
                401 -> "API key inválida. Revisa la configuración."
                404 -> "Recurso no encontrado."
                429 -> "Demasiadas peticiones. Espera un momento."
                in 500..599 -> "Error en el servidor. Inténtalo más tarde."
                else -> "Error de red (${e.code()})."
            }
            else -> e.message ?: "Error desconocido."
        }
    }
}
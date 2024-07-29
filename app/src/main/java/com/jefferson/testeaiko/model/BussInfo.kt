package com.example.app_test.model

import com.google.gson.annotations.SerializedName

data class BussInfo(
    @SerializedName("cl") val codigoIdentificadorLinha: Long? = 0,
    @SerializedName("lc") val linhaCircular: Boolean? = false,
    val lt: String? = "",
    @SerializedName("sl") val sentidoLinha: Int? = 0,
    @SerializedName("tp") val terminalPrimario: String? = "",
    @SerializedName("ts") val terminalSecundario: String? = ""
)
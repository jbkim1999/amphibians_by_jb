package com.example.amphibiansbyjb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // allowing JSON to be converted into this class and vice versa, using kotlin serialization library
data class AmphibiansModel(
    val name: String,
    val type: String, // Could use enum
    val description: String,
    @SerialName(value = "img_src")
    val imrSrc: String
)

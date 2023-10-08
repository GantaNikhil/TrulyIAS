package com.example.truly

import java.io.Serializable

data class ExperimentsModel(
    var expName : String? = null,
    var exp : Int? = 0
) : Serializable

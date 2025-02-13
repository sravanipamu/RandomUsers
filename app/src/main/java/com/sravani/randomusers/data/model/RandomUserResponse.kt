package com.sravani.randomusers.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results")
    val  results : List<User>,
    @SerializedName("info")
    val  info : Info)

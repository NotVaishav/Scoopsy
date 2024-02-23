package com.example.scoopsy.data

import androidx.annotation.StringRes
import com.example.scoopsy.R

data class IceCreamType(
    @StringRes val type: Int,
    val price: Double
)


val IceCreamTypes = listOf(
    IceCreamType(R.string.cone, R.string.cone_price.toDouble()),
    IceCreamType(R.string.cup, R.string.cup_price.toDouble()),
    IceCreamType(R.string.waffle, R.string.waffle_price.toDouble()),
)
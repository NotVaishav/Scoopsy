package com.example.scoopsy.data

import androidx.annotation.StringRes
import com.example.scoopsy.R

data class IceCreamType(
    @StringRes val type: Int,
    val price: Double
)


val IceCreamTypes = listOf(
    IceCreamType(R.string.cup, 0.00),
    IceCreamType(R.string.cone, 1.00),
    IceCreamType(R.string.waffle, 1.50),
    IceCreamType(R.string.sundae, 2.00),
)

fun List<IceCreamType>.getType(type: Int): IceCreamType {
    return this.find { it.type == type } ?: IceCreamType(
        R.string.cup,
        0.00
    )
}
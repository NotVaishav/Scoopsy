package com.example.scoopsy.data

import androidx.annotation.StringRes
import com.example.scoopsy.R

data class IceCreamType(
    @StringRes val type: Int,
    @StringRes val price: Int
)


val IceCreamTypes = listOf(
    IceCreamType(R.string.cone, R.string.cone_price),
    IceCreamType(R.string.cup, R.string.cup_price),
    IceCreamType(R.string.waffle, R.string.waffle_price),
    IceCreamType(R.string.sundae, R.string.sundae_price),
)

fun List<IceCreamType>.getType(type: Int): IceCreamType {
    return this.find { it.type == type } ?: IceCreamType(
        R.string.cup,
        R.string.cup_price
    )
}
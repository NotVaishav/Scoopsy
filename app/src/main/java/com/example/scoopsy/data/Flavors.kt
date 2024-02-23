package com.example.scoopsy.data

import androidx.annotation.StringRes
import com.example.scoopsy.R

data class Flavor(
    @StringRes val name: Int,
    val price: Double
)

val Flavors = listOf(
    Flavor(R.string.vanilla, R.string.vanilla_price.toDouble()),
    Flavor(R.string.strawberry, R.string.strawberry_price.toDouble()),
    Flavor(R.string.chocolate, R.string.chocolate_price.toDouble()),
    Flavor(R.string.pistachio, R.string.pistachio_price.toDouble()),
    Flavor(R.string.caramel, R.string.caramel_price.toDouble()),
)

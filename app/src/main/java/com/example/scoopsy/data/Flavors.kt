package com.example.scoopsy.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.scoopsy.R

data class Flavor(
    @StringRes val name: Int,
    @StringRes val price: Int,
    @DrawableRes val image: Int,
    val description: String
)

val descriptionCommonFlavor =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi u."

val Flavors = listOf(
    Flavor(
        R.string.vanilla,
        R.string.vanilla_price,
        R.drawable.vanilla_flavor,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.strawberry,
        R.string.strawberry_price,
        R.drawable.strawberry_scoop,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.chocolate,
        R.string.chocolate_price,
        R.drawable.chocolate_scoop,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.pistachio,
        R.string.pistachio_price,
        R.drawable.pistachio_scoop,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.blueberry,
        R.string.blueberry_price,
        R.drawable.blueberry_flavor,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.caramel,
        R.string.caramel_price,
        R.drawable.caramel_sorbet_flavor,
        descriptionCommonFlavor
    ),
    Flavor(
        R.string.almond_fudge,
        R.string.almond_fudge_price,
        R.drawable.almond_fudge_scoop,
        descriptionCommonFlavor
    ),
)

fun List<Flavor>.getFlavor(name: Int): Flavor {
    return this.find { it.name == name } ?: Flavor(
        R.string.vanilla,
        R.string.vanilla_price,
        R.drawable.vanilla_flavor,
        descriptionCommon
    )
}
package com.example.scoopsy.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.scoopsy.R

data class Item(
    @StringRes val name: Int,
    @StringRes val subType: Int? = null,
    @StringRes val price: Int,
    @DrawableRes val image: Int,
    val description: String,
    val isPopular: Boolean = false
)

val descriptionCommon =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi u."


val waffleConeType = R.string.waffle
val coneType = R.string.cone
val sundaeType = R.string.sundae

val vanillaWaffleCone = Item(
    R.string.vanilla,
    waffleConeType,
    R.string.vwc_price,
    R.drawable.vanilla_waffle_cone_pi,
    descriptionCommon,
    true
)
val strawberryDrizzle = Item(
    R.string.strawberry,
    sundaeType,
    R.string.ss_price,
    R.drawable.strawberry_drizzle_pi,
    descriptionCommon,
    true
)
val strawberryWaffleCone = Item(
    R.string.strawberry,
    waffleConeType,
    R.string.swc_price,
    R.drawable.strawberry_waffle_cone_pi,
    descriptionCommon,
    true
)
val chocolateSundae = Item(
    R.string.chocolate,
    R.string.brownie,
    R.string.cb_price,
    R.drawable.chocolate_sundae_pi,
    descriptionCommon,
    true
)
val bananaSplit = Item(
    R.string.vanilla,
    sundaeType,
    R.string.bs_price,
    R.drawable.banana_split_pi,
    descriptionCommon,
    true
)

val popularItems = listOf<Item>(
    vanillaWaffleCone,
    strawberryDrizzle,
    chocolateSundae,
    strawberryWaffleCone,
    bananaSplit,
)

val normalItems = listOf<Item>(
    Item(
        name = R.string.vanilla,
        price = R.string.vanilla_price,
        image = R.drawable.vanilla_flavor,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.strawberry,
        price = R.string.strawberry_price,
        image = R.drawable.strawberry_scoop,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.chocolate,
        price = R.string.chocolate_price,
        image = R.drawable.chocolate_scoop,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.pistachio,
        price = R.string.pistachio_price,
        image = R.drawable.pistachio_scoop,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.blueberry,
        price = R.string.blueberry_price,
        image = R.drawable.blueberry_flavor,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.caramel,
        price = R.string.caramel_price,
        image = R.drawable.caramel_sorbet_flavor,
        description = descriptionCommonFlavor
    ),
    Item(
        name = R.string.almond_fudge,
        price = R.string.almond_fudge_price,
        image = R.drawable.almond_fudge_scoop,
        description = descriptionCommonFlavor
    ),
)


val Items = popularItems + normalItems
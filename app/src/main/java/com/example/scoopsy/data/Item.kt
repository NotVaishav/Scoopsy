package com.example.scoopsy.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.scoopsy.R

data class Item(
    @StringRes val name: Int,
    @StringRes val subType: Int? = null,
    val price: Double,
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
    10.00,
    R.drawable.vanilla_waffle_cone_pi,
    descriptionCommon,
    true
)
val strawberryDrizzle = Item(
    R.string.strawberry,
    sundaeType,
    11.00,
    R.drawable.strawberry_drizzle_pi,
    descriptionCommon,
    true
)
val strawberryWaffleCone = Item(
    R.string.strawberry,
    waffleConeType,
    10.00,
    R.drawable.strawberry_waffle_cone_pi,
    descriptionCommon,
    true
)
val chocolateSundae = Item(
    R.string.chocolate,
    R.string.brownie,
    15.00,
    R.drawable.chocolate_sundae_pi,
    descriptionCommon,
    true
)
val bananaSplit = Item(
    R.string.vanilla,
    sundaeType,
    11.00,
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
        name = R.string.strawberry,
        price = 5.00,
        image = R.drawable.strawberry_scoop,
        description = descriptionCommon
    ),
    Item(
        name = R.string.chocolate,
        price = 6.50,
        image = R.drawable.chocolate_scoop,
        description = descriptionCommon
    ),
    Item(
        name = R.string.pistachio,
        price = 6.00,
        image = R.drawable.pistachio_scoop,
        description = descriptionCommon
    ),
    Item(
        name = R.string.blueberry,
        price = 6.00,
        image = R.drawable.blueberry_flavor,
        description = descriptionCommon
    ),
    Item(
        name = R.string.mango,
        price = 7.00,
        image = R.drawable.mango_scoop,
        description = descriptionCommon
    ),
    Item(
        name = R.string.blackberry,
        price = 7.00,
        image = R.drawable.blackberry_scoop,
        description = descriptionCommon
    ),
    Item(
        name = R.string.vanilla,
        price = 4.00,
        image = R.drawable.vanilla_scoop,
        description = descriptionCommon
    )
)


val Items = popularItems + normalItems
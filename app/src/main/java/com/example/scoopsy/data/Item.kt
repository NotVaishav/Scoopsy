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


val waffleConeType = R.string.waffle
val sundaeType = R.string.sundae

val vanillaWaffleCone = Item(
    R.string.vanilla,
    waffleConeType,
    10.00,
    R.drawable.vanilla_waffle_cone_pi,
    "Experience the nostalgic delight of our Vanilla Waffle Cone ice cream, where velvety vanilla meets the irresistible crunch of a freshly baked waffle cone. Indulge in its rich, creamy texture that promises to transport you to ice cream bliss with every scoop.",
    true
)
val strawberryDrizzle = Item(
    R.string.strawberry,
    sundaeType,
    11.00,
    R.drawable.strawberry_drizzle_pi,
    "Savor the lusciousness of Strawberry Drizzle, where ripe strawberries meet creamy indulgence in a delightful swirl of flavor. Dive into a burst of fruity freshness and creamy goodness in every spoonful of this delectable treat.",
    true
)
val strawberryWaffleCone = Item(
    R.string.strawberry,
    waffleConeType,
    10.00,
    R.drawable.strawberry_waffle_cone_pi,
    "\n" +
            "Indulge in the summery sweetness of Strawberry Waffle Cone, where succulent strawberries harmonize with crispy waffle cones for a delightful frozen dessert experience.",
    true
)
val chocolateSundae = Item(
    R.string.chocolate,
    R.string.brownie,
    15.00,
    R.drawable.chocolate_sundae_pi,
    "Dive into the rich and velvety world of Chocolate Sundae, where every spoonful unveils a decadent blend of creamy chocolate goodness and tantalizing toppings.",
    true
)
val bananaSplit = Item(
    R.string.vanilla,
    sundaeType,
    11.00,
    R.drawable.banana_split_pi,
    "Satisfy your cravings with the classic delight of Banana Split, featuring ripe bananas nestled between scoops of creamy ice cream, topped with luscious chocolate, velvety caramel, and a sprinkle of crunchy nuts.",
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
        description = "Indulge in the fruity explosion of Strawberry, where each scoop offers a burst of vibrant flavor reminiscent of sun-ripened berries picked at the peak of freshness."
    ),
    Item(
        name = R.string.chocolate,
        price = 6.50,
        image = R.drawable.chocolate_scoop,
        description = "Delight in the timeless allure of Chocolate, where rich cocoa blends harmoniously with creamy goodness to create a decadent treat for the senses."
    ),
    Item(
        name = R.string.pistachio,
        price = 6.00,
        image = R.drawable.pistachio_scoop,
        description = "Savor the nutty charm of Pistachio, where every spoonful unveils the delicate balance of roasted pistachios in a creamy base, offering a satisfying crunch with each bite."
    ),
    Item(
        name = R.string.blueberry,
        price = 6.00,
        image = R.drawable.blueberry_flavor,
        description = "Embark on a berry-filled adventure with Blueberry, where plump, juicy berries are swirled into creamy goodness, delivering a delightful burst of sweetness in every mouthful."
    ),
    Item(
        name = R.string.mango,
        price = 7.00,
        image = R.drawable.mango_scoop,
        description = "Transport your taste buds to a tropical paradise with Mango, where the succulent sweetness of ripe mangoes is perfectly captured in a creamy indulgence that's sure to refresh and delight."
    ),
    Item(
        name = R.string.blackberry,
        price = 7.00,
        image = R.drawable.blackberry_scoop,
        description = "Experience the bold tanginess of Blackberry, where ripe, juicy berries are blended into a creamy concoction, offering a tantalizing burst of flavor with every spoonful."
    ),
    Item(
        name = R.string.vanilla,
        price = 4.00,
        image = R.drawable.vanilla_scoop,
        description = "Indulge in the timeless elegance of Vanilla, where the delicate fragrance and creamy richness of pure vanilla beans create a comforting and classic treat that never goes out of style."
    )
)


val Items = popularItems + normalItems
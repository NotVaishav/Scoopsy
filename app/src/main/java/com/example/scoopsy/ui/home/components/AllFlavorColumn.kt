package com.example.scoopsy.ui.home.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scoopsy.data.Items
import com.example.scoopsy.ui.ScoopsyViewModel

@Composable
fun AllFlavorColumn(
    modifier: Modifier = Modifier,
    scoopsyViewModel: ScoopsyViewModel,
    onFlavorClick: () -> Unit,
    context: Context
) {
    Column {
        for (item in Items.filter { !it.isPopular }) {
            FlavorCard(flavor = item, onItemClick = {
                onFlavorClick()
                scoopsyViewModel.setCurrentItem(item)

            }, context = context)
            Spacer(modifier = modifier.size(15.dp))
        }
    }
}
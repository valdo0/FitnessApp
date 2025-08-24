package com.example.fitnessapp.ui.components

import android.graphics.fonts.Font
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    imageUrl: String? = null,
    @DrawableRes placeholderResId: Int? = R.drawable.placeholder_food,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageUrl != null || placeholderResId != null) {
                if (LocalInspectionMode.current && placeholderResId != null) {
                    Image(
                        painter = painterResource(id = placeholderResId),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                } else {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl ?: placeholderResId)
                            .crossfade(true)
                            .placeholder(placeholderResId ?: R.drawable.profile_placeholder)
                            .error(placeholderResId ?: R.drawable.profile_placeholder)
                            .build(),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape))
                }
                Spacer(modifier = Modifier.width(16.dp))
            }

            // Sección de Texto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold

                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CardListItemPreview() {
    FitnessAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            CardListItem(
                title = "Ensalada Fresca de Verano",
                subtitle = "Una deliciosa y saludable ensalada con muchos vegetales frescos y un aderezo ligero.",
                placeholderResId = R.drawable.placeholder_food,
                onClick = { System.out.println("Clicked Ensalada") }
            )
            Spacer(Modifier.height(8.dp))
            CardListItem(
                title = "Salmón al Horno",
                subtitle = "Rico en Omega-3.",
                imageUrl = "https://example.com/salmon.jpg",
                placeholderResId = R.drawable.placeholder_food,
                onClick = { System.out.println("Clicked Salmon") }
            )
            Spacer(Modifier.height(8.dp))
            CardListItem(
                title = "Batido de Proteínas",
                placeholderResId = R.drawable.placeholder_food,
                onClick = { System.out.println("Clicked Batido") }
            )
        }
    }
}


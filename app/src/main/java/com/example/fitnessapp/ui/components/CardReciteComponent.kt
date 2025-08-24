package com.example.fitnessapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun CardRecite(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    imageUrl: String? = null,
    @DrawableRes placeholderResId: Int? = R.drawable.placeholder_food,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column (
            modifier = modifier.fillMaxWidth()
        ){

            if (imageUrl != null || placeholderResId != null) {
                if (LocalInspectionMode.current) {
                    if (placeholderResId != null) {
                        Image(
                            painter = painterResource(id = placeholderResId),
                            contentDescription = title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(4f / 5f)
                                .clip(MaterialTheme.shapes.medium)
                        )
                    }
                } else {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .placeholder(placeholderResId ?: R.drawable.placeholder_food)
                            .error(placeholderResId ?: R.drawable.placeholder_food)
                            .build(),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 5f)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
            Spacer(modifier=Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyLarge ,
                        color = MaterialTheme.colorScheme.onSecondary,

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardRecitePreview() {
    FitnessAppTheme {
        CardRecite(
            title = "Ensalada Fresca de Verano",
            subtitle = "Una deliciosa y saludable ensalada.",
            placeholderResId = R.drawable.placeholder_food
        )
    }
}


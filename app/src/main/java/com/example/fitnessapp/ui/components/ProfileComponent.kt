package com.example.fitnessapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fitnessapp.R

@Composable
fun CircularProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    placeholderResId: Int? = R.drawable.profile_placeholder,
    imageSize: Dp = 96.dp,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier
            .size(imageSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .then(
                if (borderWidth > 0.dp) {
                    Modifier.border(borderWidth, borderColor, CircleShape)
                } else {
                    Modifier
                }
            )
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .placeholder(placeholderResId ?: R.drawable.profile_placeholder)
                    .error(placeholderResId ?: R.drawable.profile_placeholder)
                    .build(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        } else if (placeholderResId != null) {
            Image(
                painter = painterResource(id = placeholderResId),
                contentDescription = "Profile Picture Placeholder",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CircularProfileImagePreviewDrawable() {
    MaterialTheme {
        CircularProfileImage(
            placeholderResId = R.drawable.profile_placeholder,
            imageSize = 100.dp,
            borderWidth = 3.dp,
            borderColor = Color.Magenta
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProfileImagePreviewUrl() {
    MaterialTheme {
        CircularProfileImage(
            // Reemplaza con una URL real para probar
            imageUrl = "https://via.placeholder.com/150",
            placeholderResId = R.drawable.profile_placeholder,
            imageSize = 80.dp,
            borderWidth = 0.dp // Sin borde
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProfileImagePreviewEmpty() {
    MaterialTheme {
        CircularProfileImage(
            imageUrl = null,
            placeholderResId = null,
            imageSize = 60.dp,
            borderWidth = 2.dp,
            borderColor = Color.Green
        )
    }
}

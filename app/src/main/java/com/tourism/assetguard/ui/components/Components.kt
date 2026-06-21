package com.tourism.assetguard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.tourism.assetguard.ui.theme.*

@Composable
fun GlassyCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = if (isSystemInDarkTheme()) SlateCard else LightSurface,
    borderColor: Color = if (isSystemInDarkTheme()) Color(0x1BFFFFFF) else Color(0x0E000000),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, borderColor, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor.copy(alpha = 0.95f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        content = content
    )
}

@Composable
fun TourismTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    val dark = isSystemInDarkTheme()
    val textCol = if (dark) SlateTextPrimary else LightTextPrimary
    val containerCol = if (dark) SlateSurface else LightCard.copy(alpha = 0.5f)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = if (dark) SlateTextSecondary else LightTextSecondary) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textCol,
            unfocusedTextColor = textCol,
            focusedContainerColor = containerCol,
            unfocusedContainerColor = containerCol,
            focusedBorderColor = TealPrimary,
            unfocusedBorderColor = if (dark) Color(0x33FFFFFF) else Color(0x1A000000),
            cursorColor = TealPrimary
        ),
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null, tint = TealPrimary) }
        },
        trailingIcon = trailingIcon
    )
}

@Composable
fun LushPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val brush = Brush.horizontalGradient(
        colors = listOf(TealPrimary, TealDark)
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(14.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    stars: Int = 5,
    onRatingChanged: ((Double) -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..stars) {
            val isFilled = rating >= i
            val icon = Icons.Filled.Star

            val tint = if (isFilled) {
                WarningAmber
            } else {
                if (isSystemInDarkTheme()) Color(0x33FFFFFF) else Color(0x1A000000)
            }

            Icon(
                imageVector = icon,
                contentDescription = "Star $i",
                tint = tint,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(enabled = onRatingChanged != null) {
                        onRatingChanged?.invoke(i.toDouble())
                    }
            )
        }
    }
}

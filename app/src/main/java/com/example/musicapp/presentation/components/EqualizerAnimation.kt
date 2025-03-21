package com.example.musicapp.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun EqualizerAnimation() {
    val bars = 6
    val animValues = List(bars) { remember { Animatable(Random.nextFloat() * 20f + 10f) } }

    LaunchedEffect(Unit) {
        animValues.forEachIndexed { index, anim ->
            launch {
                while (true) {
                    anim.animateTo(
                        targetValue = Random.nextFloat() * 30f + 10f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 300 + (index * 50), easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        animValues.forEach { anim ->
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(anim.value.dp)
                    .background(Color.Green, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}

package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class Welcome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Container()
            }
        }
    }
}

@Composable
fun Background(content: @Composable () -> Unit) {
    val isDarkMode = !MaterialTheme.colors.isLight
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CoilImage(
            data = if (isDarkMode) R.drawable.ic_dark_welcome_bg else R.drawable.ic_light_welcome_bg,
            contentDescription = "welcome Background",
            contentScale = ContentScale.FillBounds
        )
        content()
    }
}

@Composable
fun Container() {
    val isDarkMode = !MaterialTheme.colors.isLight
    Surface(
        color = MaterialTheme.colors.primary,
    ) {
        Background {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                CoilImage(
                    data = if (isDarkMode) R.drawable.ic_dark_welcome_illos else R.drawable.ic_light_welcome_illos,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 56.dp, bottom = 48.dp)
                        .absoluteOffset(x = 64.dp)
                        .fillMaxWidth()
                )
                CoilImage(
                    data = if (isDarkMode) R.drawable.ic_dark_logo else R.drawable.ic_light_logo,
                    contentDescription = "Logo",
                )
                Text(
                    text = "Beautiful home garden solutions",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    lineHeight = 32.sp
                )
                PrimaryButton(
                    label = "Create account",
                    modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
                ) {}
                TextButton(onClick = { }) {
                    Text(text = "Log in", color = MaterialTheme.colors.secondary)
                }
            }
        }
    }
}

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    Button(
        contentPadding = PaddingValues(16.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(text = label, color = MaterialTheme.colors.background)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    MyTheme {
        Container()
    }
}
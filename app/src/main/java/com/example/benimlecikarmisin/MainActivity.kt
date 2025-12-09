package com.example.benimlecikarmisin

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.benimlecikarmisin.ui.theme.BenimleCikarMisinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BenimleCikarMisinTheme {
                Greeting()
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current

            var evetButtonWidth by remember { mutableStateOf(100.dp) }
            var evetButtonHeight by remember { mutableStateOf(50.dp) }
            var evetDedi by remember { mutableStateOf(false) }
            var listIndex by remember { mutableIntStateOf(0) }
            val list = mutableStateListOf<String>(
                "hayır",
                "üzüldüm :(",
                "bak emin misin",
                "yapma bak",
                "sakın yapma",
                "e yok artık",
                "inanmıyorum",
                "ciddi misin",
                "e artık dur be",
                "e artık dur be")

            val animatedWidth by animateDpAsState(
                targetValue = evetButtonWidth,
                label = "widthAnimation"
            )
            val animatedHeight by animateDpAsState(
                targetValue = evetButtonHeight,
                label = "heightAnimation"
            )

            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()

            if (!evetDedi) {
                Text(text = "Benimle Çıkar Mısın?", fontSize = 25.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }

            if (!evetDedi) {
                Spacer(modifier = Modifier.size(25.dp))
            }

            if (!evetDedi) {
                // EVET Butonu
                Button(
                    onClick = {
                        evetDedi = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(30, 200, 30),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.size(width = animatedWidth, height = animatedHeight)
                ) {
                    Text(text = "EVET")
                }
            }

            if (!evetDedi) {
                // HAYIR Butonu
                Button(
                    onClick = {
                        evetButtonWidth += 200.dp
                        evetButtonHeight += 95.dp
                        listIndex++
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(200, 30, 30),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(text = list[listIndex])
                }
            }

            // GIF Resmi
            AsyncImage(
                model = if (!evetDedi) R.drawable.hayir_gif else R.drawable.evet_gif,
                imageLoader = imageLoader,
                contentDescription = "Animasyonlu GIF",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BenimleCikarMisinTheme {
        Greeting()
    }
}
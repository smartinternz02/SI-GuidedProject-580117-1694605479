package com.example.assignment3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    var selectedWebsite by remember { mutableStateOf<Website?>(null) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back), // Replace with your background image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
            ) {
                // Lottie Animation
                LottieAnimationFromRaw()
            }
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 26.dp)
            )

            Spacer(modifier = Modifier.height(66.dp))

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        "Username",
                        modifier = Modifier,
                        textAlign = TextAlign.Start
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        "Password",
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(66.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (website in Website.values()) {
                    WebsiteImage(
                        website = website,
                        isSelected = website == selectedWebsite,
                        onWebsiteSelected = { selectedWebsite = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Open Button
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        selectedWebsite?.let { website ->
                            val url = website.url
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(url)
                            context.startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Username and Password are required.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Open")
            }
        }
    }
}

@Composable
fun LottieAnimationFromRaw() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever // Set to loop infinitely
    )

    Spacer(modifier = Modifier.height(30.dp))
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 60.dp)
            .offset(y = (-10).dp)
    )
}


@Composable
fun WebsiteImage(
    website: Website,
    isSelected: Boolean,
    onWebsiteSelected: (Website) -> Unit
) {
    val density = LocalDensity.current.density
    val modifier = Modifier
        .size(72.dp)
        .padding(4.dp)
        .clickable { onWebsiteSelected(website) }
        .background(
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
            shape = MaterialTheme.shapes.medium
        )

    Image(
        painter = painterResource(id = website.iconResId),
        contentDescription = website.name,
        modifier = modifier,
        alignment = Alignment.Center
    )
}

enum class Website(val iconResId: Int, val url: String) {
    GOOGLE(R.drawable.google_logo, "https://www.google.com"),
    YOUTUBE(R.drawable.youtube_logo_wine, "https://www.youtube.com"),
    FACEBOOK(R.drawable.facebook, "https://www.facebook.com"),
    TWITTER(R.drawable.twitter_icon, "https://www.twitter.com")
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    Assignment3Theme {
        AppContent()
    }
}

package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.ui.theme.FoodAppTheme
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
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
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF5B4432)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = statusBarColor,
                darkIcons = false
            )
        }
        Image(
            painter = painterResource(id = R.drawable.back1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
            ) {
                LottieAnimationFromRaw()
            }
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 26.dp)
            )

            Spacer(modifier = Modifier.height(46.dp))

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


            Spacer(modifier = Modifier.height(26.dp))

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        val i =Intent(context,HomeActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(i)
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
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3366FF)
                )
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun LottieAnimationFromRaw() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.food))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
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

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    FoodAppTheme  {
        AppContent()
    }
}

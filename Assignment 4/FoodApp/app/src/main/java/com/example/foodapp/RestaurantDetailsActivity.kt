package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FoodAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class RestaurantDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val intent = intent
                    val restaurantName = intent.getStringExtra("restaurantName")
                    val restaurantImageResId = intent.getIntExtra("restaurantImage", 0)
                    val restaurantRating = intent.getFloatExtra("restaurantRating", 0.0f)
                    val timeRequired = intent.getStringExtra("timeRequired")

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        RestaurantDetails(
                            restaurantName = restaurantName.orEmpty(),
                            restaurantImage = restaurantImageResId,
                            restaurantRating = restaurantRating,
                            timeRequired = timeRequired.orEmpty()
                        )

                    }
                }
            }
        }
    }
}

 @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetails(
    restaurantName: String,
    restaurantImage: Int,
    restaurantRating: Float,
    timeRequired: String
) {
    val statusBarColor = Color(0xFFfffbfc)
    val systemUiController = rememberSystemUiController()

     Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = statusBarColor,
                darkIcons = true
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            Image(
                painter = painterResource(id = restaurantImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = restaurantName,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.White
                        ),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Rating: $restaurantRating / 5",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        ),
                        modifier = Modifier.padding(end = 16.dp)
                    )

                    Text(
                        text = "Time: $timeRequired",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ) {
                FoodItemCard(name = "Burger", price = "$4.99", imageResId = R.drawable.burger)
                FoodItemCard(name = "Pizza", price = "$6.99", imageResId = R.drawable.pizza)
                FoodItemCard(name = "Chicken Biryani", price = "$14.99", imageResId = R.drawable.chicken)
                FoodItemCard(name = "Salad", price = "$2.99", imageResId = R.drawable.pasta)
                FoodItemCard(name = "Veg Pakoda", price = "$4.99", imageResId = R.drawable.salad)
                FoodItemCard(name = "Veg Curry", price = "$4.99", imageResId = R.drawable.veg_curry)

            }
        }
}

@Composable
fun FoodItemCard( name: String, price: String, imageResId: Int) {
    var quantity by remember { mutableStateOf(0) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp, 64.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
            ) {
                Image(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            if (quantity > 0) {
                                quantity--
                            }
                        }
                        .size(22.dp)
                )

                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            quantity++
                        }
                        .size(22.dp)
                )

            }
        }
}


@Preview(showBackground = true)
@Composable
fun RestaurantDetailsPreview() {
    FoodAppTheme {
        RestaurantDetails(
            restaurantName = "Tasty Delights",
            restaurantImage = R.drawable.rest1,
            restaurantRating = 4.5f,
            timeRequired = "35 min"
        )
    }
}


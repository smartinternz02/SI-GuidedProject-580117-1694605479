package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FoodAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RestaurantList()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantList() {
    val scrollState = rememberScrollState()
    val scrollState1 = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        var name by remember { mutableStateOf("") }

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(
                    "Mumbai",
                    modifier = Modifier,
                    textAlign = TextAlign.Start
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(text = "What's on your mind ?",
            Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold )

        Spacer(modifier = Modifier.height(19.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
        state = scrollState1
        ) {
            items(restaurantItems) { restaurant ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = restaurant.imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = restaurant.name,
                        Modifier.padding(start = 5.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Restaurants to explore",
            Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold )

        Spacer(modifier = Modifier.height(15.dp))

        RestaurantItem(
            restaurantName = "Tasty Delights",
            restaurantAddress = "123 Main St",
            restaurantRating = 4.5f,
            timeRequired = "35 min",
            restaurantImage = R.drawable.rest1
        )


        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "Cafe Sunshine",
            restaurantAddress = "456 Elm St",
            restaurantRating = 4.0f,
            timeRequired = "13 min",
            restaurantImage = R.drawable.rest2
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "Spice Haven",
            restaurantAddress = "Das Nagar",
            restaurantRating = 4.3f,
            timeRequired = "5 min",
            restaurantImage = R.drawable.rest3
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "Fusion Flavors",
            restaurantAddress = "6 Jor Rt",
            restaurantRating = 4.6f,
            timeRequired = "30 min",
            restaurantImage = R.drawable.rest4
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "Thai Taste Temptation",
            restaurantAddress = "Jor Nag",
            restaurantRating = 4.8f,
            timeRequired = "45 min",
            restaurantImage = R.drawable.rest5
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "Green Garden Bistro",
            restaurantAddress = "23 St Das",
            restaurantRating = 4.2f,
            timeRequired = "32 min",
            restaurantImage = R.drawable.rest6
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantItem(
            restaurantName = "The Pasta Palace",
            restaurantAddress = "4 Shiva. jip",
            restaurantRating = 3.0f,
            timeRequired = "55 min",
            restaurantImage = R.drawable.rest7
        )
    }
}

@Composable
fun RestaurantItem(
    restaurantName: String,
    restaurantAddress: String,
    restaurantRating: Float,
    timeRequired: String,
    restaurantImage: Int
) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val statusBarColor = Color(0xFFfffbfc)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, RestaurantDetailsActivity::class.java)
                intent.putExtra("restaurantName", restaurantName)
                intent.putExtra("restaurantAddress", restaurantAddress)
                intent.putExtra("restaurantRating", restaurantRating)
                intent.putExtra("timeRequired", timeRequired)
                intent.putExtra("restaurantImage", restaurantImage)
                context.startActivity(intent)
            },
    verticalAlignment = Alignment.CenterVertically
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = statusBarColor,
                darkIcons = true
            )
        }
        Image(
            painter = painterResource(id = restaurantImage),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = restaurantName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = restaurantAddress, color = Color.Gray)
            Text(text = "Rating: $restaurantRating / 5")
            Text(text = "Time: $timeRequired")
        }
    }
}

data class RestaurantItem(
    val name: String,
    val imageResId: Int
)

val restaurantItems = listOf(
    RestaurantItem("Double Burger", R.drawable.burger),
    RestaurantItem("Pizza", R.drawable.pizza),
    RestaurantItem("Chicken Biryani", R.drawable.chicken),
    RestaurantItem("Pasta", R.drawable.pasta),
    RestaurantItem("Salad", R.drawable.salad),
    RestaurantItem("Single Burger", R.drawable.burger2),
)

@Preview(showBackground = true)
@Composable
fun RestaurantListPreview() {
    FoodAppTheme {
        RestaurantList()
    }
}



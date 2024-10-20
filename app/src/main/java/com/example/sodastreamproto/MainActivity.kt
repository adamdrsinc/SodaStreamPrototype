package com.example.sodastreamproto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodastreamproto.ui.theme.SodaStreamProtoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var strawberryCream = Ingredient("Strawberry Cream")
        var vanilla = Ingredient("Vanilla")

        var drink1 = Drink(arrayListOf(
            Pair(strawberryCream, 2),
            Pair(vanilla, 1)
        ),
            drinkName = "Cweamer",
            drinkPrice = 13.82,
            drinkQuantity = 1)

        var drink2 = Drink(arrayListOf(
            Pair(strawberryCream, 1),
            Pair(vanilla, 3)
        ),
            drinkName = "Tonker",
            drinkPrice = 9.82,
            drinkQuantity = 2)

        Basket.basketDrinks.add(drink1)
        Basket.basketDrinks.add(drink2)
        Basket.basketDrinks.add(drink1)
        Basket.basketDrinks.add(drink2)


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SodaStreamProtoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    ShoppingBasket()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SodaStreamProtoPreview() {
    SodaStreamProtoTheme {
        ShoppingBasket()
    }
}

@Composable
fun ShoppingBasket(modifier: Modifier = Modifier){
    val shoppingBasketHeaderText = "Shopping Cart"
    val shoppingBasketHeaderImage = painterResource(R.drawable.bg_compose_background)

    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp
    val context = LocalContext.current

    val currentActivity = context as Activity

    val modifierHeader = Modifier
        .padding(16.dp)

    val modifierShoppingCartColumn = Modifier
        .background(Color.White)
        .size((screenHeight * 0.80).dp)
        .verticalScroll(rememberScrollState())


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifierShoppingCartColumn
    ) {
        Image(
            painter = shoppingBasketHeaderImage,
            contentDescription = ""
        )
        Text(
            text = shoppingBasketHeaderText,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = modifierHeader
        )



        LazyColumn(
            modifier = Modifier
                .height(screenHeight.dp)
                .width(screenWidth.dp)
        ){
            itemsIndexed(Basket.basketDrinks){
                id, item ->
                Text(
                    text = item.drinkName,
                    modifier = Modifier
                        .padding(16.dp)
                        .width((screenWidth * 0.90).dp)
                        .height((screenHeight * 0.25).dp)
                        .border(2.dp, Color.Black)
                        .padding(24.dp)
                        .clickable{
                            Toast.makeText(context, "${item.drinkName} clicked", Toast.LENGTH_SHORT).show()
                            val intent = Intent(currentActivity, EditDrinkActivity::class.java)
                            currentActivity.startActivity(intent)
                        }
                )

            }


        }
    }
}

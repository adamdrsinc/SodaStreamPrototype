package com.example.sodastreamproto

import android.app.Activity
import android.content.Intent
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodastreamproto.ui.theme.SodaStreamProtoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var strawberryCream = Ingredient("Strawberry Cream")
        var vanilla = Ingredient("Vanilla")
        var coconut = Ingredient("Coconut")
        var creamer = Ingredient("Creamer")

        val TEMP_DRINK_INGREDIENTS = listOf<String>(
            "Coke",
            "Dr. Pepper",
            "Sprite",
            "7-up",
            "A & W"
        )

        var drink1 = Drink(arrayListOf(
            Pair(strawberryCream, 2),
            Pair(vanilla, 1),
            Pair(coconut, 1),
            Pair(creamer, 1)
        ),
            drinkName = "Cweamer",
            drinkPrice = 13.82,
            drinkQuantity = 1,
            isCustom = false,
            iceQuantity = 0)

        Basket.addDrink(drink1)

        var drink2 = Drink(arrayListOf(
            Pair(strawberryCream, 1),
            Pair(vanilla, 3)
        ),
            drinkName = "Custom 1",
            drinkPrice = 9.82,
            drinkQuantity = 2,
            isCustom = true,
            baseDrink = TEMP_DRINK_INGREDIENTS[1],
            iceQuantity = 3)

        Basket.addDrink(drink2)

        var drink3 = Drink(arrayListOf(
            Pair(strawberryCream, 1),
            Pair(vanilla, 3)
        ),
            drinkName = "Thingywingy",
            drinkPrice = 9.82,
            drinkQuantity = 2,
            isCustom = false,
            iceQuantity = 2)

        Basket.addDrink(drink3)

        var drink4 = Drink(arrayListOf(
            Pair(strawberryCream, 1),
            Pair(vanilla, 3)
        ),
            drinkName = "Custom 2",
            drinkPrice = 9.82,
            drinkQuantity = 2,
            isCustom = true,
            baseDrink = TEMP_DRINK_INGREDIENTS[3],
            iceQuantity = 5)

        Basket.addDrink(drink4)

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

        BasketItems()


    }
}

@Composable
fun BasketItems(){
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp
    val context = LocalContext.current
    val currentActivity = context as Activity


    LazyColumn(
        modifier = Modifier
            .height(screenHeight.dp)
            .width(screenWidth.dp)
    ){
        itemsIndexed(Basket.getDrinks()){
                id, item ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                .padding(16.dp)
                .width((screenWidth * 0.90).dp)
                .border(2.dp, Color.Black)
                .padding(24.dp)
                ){
                Text(
                    text = item.drinkName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                if(item.baseDrink != null){
                    Text(
                        text = "Base: ${item.baseDrink.toString()}",
                        fontSize = 16.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .width((screenWidth * 0.60).dp)){
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width((screenWidth * 0.40).dp)
                    ){
                        Text(
                            text = "Quantity:"
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width((screenWidth * 0.40).dp)
                    ){
                        Text(
                            text = item.drinkQuantity.toString(),
                        )
                    }
                }


                item.drinkIngredients.forEach {
                        ingredient ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width((screenWidth * 0.60).dp))
                    {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width((screenWidth * 0.40).dp)
                        ){
                            Text(
                                text = "${ingredient.first.ingredientName}:"
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width((screenWidth * 0.40).dp)
                        ){
                            Text(
                                text = ingredient.second.toString(),
                            )
                        }
                    }
                }

                Row(){
                    Button(
                        onClick = {
                            val intent = Intent(currentActivity, EditDrinkActivity::class.java)
                            intent.putExtra("drinkID", item.drinkID)
                            currentActivity.startActivity(intent)
                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        Text(text = "Edit")
                    }

                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        Text(text = "Delete")
                    }
                }
            }


        }
    }
}

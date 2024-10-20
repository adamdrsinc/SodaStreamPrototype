package com.example.sodastreamproto

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodastreamproto.ui.theme.SodaStreamProtoTheme
import kotlin.math.exp

class EditDrinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val drinkID = intent.getIntExtra("drinkID", -1)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SodaStreamProtoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    EditMenuComposable(drinkID = drinkID)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SodaStreamProtoTheme {
    }
}

@Composable
fun EditMenuComposable(drinkID: Int){
    var currentDrink = Basket.basketDrinks.find{ it.drinkID == drinkID }

    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height((screenHeight * 0.80).dp)
            .width((screenWidth * 0.80).dp)
    ) {
        if(currentDrink != null){
            if(currentDrink.isCustom){
                TitleText("Soda Base")
                CustomDrinkDropdown()
            }
        }

        TitleText("Syrups")
        ListOfSyrups(drinkID)

        TitleText("Ice")
        //Ice Slider

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            BackButton()
            SaveButton()
        }
    }
}

@Composable
fun ListOfSyrups(drinkID: Int){
    val currentDrink = Basket.basketDrinks.find{it.drinkID == drinkID}
    val context = LocalContext.current
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    Column(

    ) {
        currentDrink?.drinkIngredients?.forEachIndexed { index, drinkPair ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(16.dp)
                    .height((screenHeight * 0.20).dp)
                    .width((screenWidth * 0.80).dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width((screenWidth * 0.40).dp)


                ){
                    Text(text = drinkPair.first.ingredientName)
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width((screenWidth * 0.40).dp)
                ){
                    Button(
                        onClick = {
                            Toast.makeText(context, "${drinkPair.first.ingredientName} Minus clicked!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        Text(text = "-")
                    }

                    Text(text = drinkPair.second.toString(),
                        modifier = Modifier
                            .padding(4.dp))

                    Button(
                        onClick = {
                            Toast.makeText(context, "${drinkPair.first.ingredientName} Plus clicked!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        Text(text = "+")
                    }
                }

            }
        }
    }
}

@Composable
fun TitleText(text: String){
    Box(
        modifier = Modifier
            .padding(16.dp)
    ){
        Text(
            text = text.uppercase(),
            fontSize = 24.sp
        )
    }

}

@Composable
fun BackButton(){
    val context = LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context, "back clicked!", Toast.LENGTH_SHORT).show()
            val currentActivity = context as Activity
            val intent = Intent(currentActivity, MainActivity::class.java)
            currentActivity.startActivity(intent)
        }
    ){
        Text(
            text = "Back",
            fontSize = 12.sp
        )
    }
}

@Composable
fun SaveButton(){
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Save clicked!", Toast.LENGTH_SHORT).show()
        }
    ){
        Text(
            text = "Save",
            fontSize = 12.sp
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDrinkDropdown(){
    var isExpanded = remember{
        mutableStateOf(false)
    }
    var selectedText = remember{
        mutableStateOf("")
    }
    var selectedOptionIndex = remember{
        mutableIntStateOf(0)
    }

    //TODO: Delete this later.
    val TEMP_DRINK_INGREDIENTS = listOf<String>(
        "Coke",
        "Dr. Pepper",
        "Sprite",
        "7-up",
        "A & W"
    )

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = {isExpanded.value = !isExpanded.value},
    ) {
        TextField(
            value = TEMP_DRINK_INGREDIENTS[selectedOptionIndex.intValue],
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = {isExpanded.value = false},
        ) {
            TEMP_DRINK_INGREDIENTS.forEachIndexed {
                    index, baseName ->
                DropdownMenuItem(
                    text = {
                        Text(text = baseName)
                    },
                    onClick = {
                        selectedText.value = baseName
                        selectedOptionIndex.value = index
                        isExpanded.value = false
                    }
                )
            }
        }
    }


}



@Composable
fun DisplayDrinkID(drinkID: Int){
    Text(
        text = if(drinkID != -1) "Drink ID: $drinkID" else "Drink not valid"
    )
}
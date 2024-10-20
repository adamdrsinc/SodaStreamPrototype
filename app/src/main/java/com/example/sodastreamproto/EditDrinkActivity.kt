package com.example.sodastreamproto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sodastreamproto.ui.theme.SodaStreamProtoTheme

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
                    DisplayDrinkID(drinkID)
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
fun DisplayDrinkID(drinkID: Int){
    Text(
        text = if(drinkID != -1) "Drink ID: $drinkID" else "Drink not valid"
    )
}
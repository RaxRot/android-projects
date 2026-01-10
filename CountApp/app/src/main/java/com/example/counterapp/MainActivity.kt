package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    TheCounterApp()
                }
            }
        }
    }
}

@Composable
fun TheCounterApp(){

    val viewModel: CounterViewModel = viewModel()

    fun incrementCounter(){
       viewModel.incrementCount()
    }

    fun decrementCounter(){
        viewModel.decrementCount()
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(text = "Count: ${viewModel.count}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Row() {
            Button(onClick = { incrementCounter()}) {
                Text("Increment")
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { decrementCounter()}) {
                Text("Decrement")
            }
        }
    }
}


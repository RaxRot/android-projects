package com.example.mvvmsimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.mvvmsimple.ui.theme.MvvmSimpleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmSimpleTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {

                    TestApp()
                }
            }
        }
    }
}

@Composable
fun TestApp(){

    val counterViewModel: CounterViewModel = viewModel()
    var countToChange by remember{ mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Total likes is ${counterViewModel.count}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                val amount = countToChange.toIntOrNull() ?: 1
                counterViewModel.incrementCount(amount)
            }) {
                Text("Like")
            }
            Button(onClick = {
                val amount = countToChange.toIntOrNull() ?: 1
                counterViewModel.decrementCount(amount)
            }) {
                Text("Dislike")
            }
            Button(onClick = { counterViewModel.resetCount() }) {
                Text("Reset")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))


        OutlinedTextField(value = countToChange,
            onValueChange = {countToChange=it},
            label = { Text("Count to change")}
        )
    }
}

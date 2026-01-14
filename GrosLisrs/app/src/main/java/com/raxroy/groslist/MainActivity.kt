package com.raxroy.groslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raxroy.groslist.ui.theme.GrosLisrsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            LazyColumnWithStickyHeader()
        }
    }
}

@Composable
fun LazyColumnWithStickyHeader() {

    val groupedItems = mapOf(
        "Fruits" to listOf(
            Item("Apple", "Apple desc", R.drawable.apple),
            Item("Banana", "Banana desc", R.drawable.bananas),
            Item("Cherry", "Cherry desc", R.drawable.cherry),
            Item("Mango", "Mango desc", R.drawable.mango),
            Item("Watermelon", "Watermelon desc", R.drawable.watermelon),
            Item("Grapes", "Grapes desc", R.drawable.grapes)
        ),
        "Vegetables" to listOf(
            Item("Carrot", "Carrot desc", R.drawable.carrot),
            Item("Lettuce", "Lettuce desc", R.drawable.lettuce),
            Item("Onion", "Onion desc", R.drawable.onion),
            Item("Broccoli", "Broccoli desc", R.drawable.brocoli),
            Item("Potato", "Potato desc", R.drawable.potato),
            Item("Tomato", "Tomato desc", R.drawable.tomato),
            Item("Pea", "Pea desc", R.drawable.pea)

        )
    )

    LazyColumn {
        groupedItems.forEach { (header, items) ->
            stickyHeader {
               MyCustomHeader(header)
            }
            items(items) { item ->
                MyCustomItem(item)
            }
        }
    }
}

@Composable
fun MyCustomHeader(title: String) {
    Text(text = title,
        fontSize = 32.sp,
        modifier = Modifier.fillMaxWidth()
            .background(Color.Green)
            .padding(8.dp), textAlign = TextAlign.Center)
}

@Composable
fun MyCustomItem(item: Item){
    Card(modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Image(painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = item.title, fontSize = 22.sp,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = item.desc, fontSize = 18.sp)


        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCustomItemPreview(){
    val item1 = Item("Apple","Very Helthy",R.drawable.applogo)
    MyCustomItem(item = item1)
}




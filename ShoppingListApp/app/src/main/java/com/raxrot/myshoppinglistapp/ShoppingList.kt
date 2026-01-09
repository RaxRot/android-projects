package com.raxrot.myshoppinglistapp

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

data class ShoppingItem(val id: Int,
                        val name: String,
                        val quantity: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    val context = LocalContext.current

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>())}
    var showDialog by remember { mutableStateOf(false)}
    var itemName by remember { mutableStateOf("")}
    var itemQuantity by remember { mutableStateOf("")}

    fun deleteItem(item: ShoppingItem){
        sItems = sItems.filter { it.id != item.id }
        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
    }

    Column(modifier =   Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center){
        Spacer(modifier = Modifier.height(75.dp))
        Button(
            onClick = {showDialog = true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Text(text = "Add Item")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
        {
            items(sItems){
                ShoppingItem(item = it,onDeleteClick = {deleteItem(it)})
            }
        }
    }

    if(showDialog){
        val context = LocalContext.current

        AlertDialog(
            onDismissRequest = {showDialog = false},
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {
                        if (itemName.isBlank() || itemQuantity.isBlank()){
                            Toast.makeText(context, "Fields cannot be blank", Toast.LENGTH_SHORT).show()
                        }else{
                            val itemQualityToInt=itemQuantity.toIntOrNull()?:0;
                            val newItem= ShoppingItem(id = sItems.size+1, name = itemName, quantity = itemQualityToInt)
                            sItems= sItems+newItem

                            itemName="";
                            itemQuantity="";
                        }
                        showDialog=false
                    }) {
                        Text("Add")
                    }
                    Button(onClick = {showDialog=false}) {
                        Text("Cancel")
                    }
                }
            },
            title = {Text(text = "Add Item")},
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                 OutlinedTextField(value = itemName, onValueChange = {itemName = it}, label = {Text("Item Name")}, modifier = Modifier.fillMaxWidth().padding(8.dp), singleLine = true)

                    Spacer(modifier = Modifier.height(16.dp))

                 OutlinedTextField(value = itemQuantity, onValueChange = {itemQuantity = it}, label = {Text("Item Quantity")}, modifier = Modifier.fillMaxWidth().padding(8.dp), singleLine = true, keyboardOptions = KeyboardOptions(
                     keyboardType = KeyboardType.Number
                 ))
                }
            }
        )
    }
}

@Composable
fun ShoppingItem(item: ShoppingItem,onDeleteClick:()->Unit){
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp).border(width = 1.dp,
        color =  Color.Black,
        shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = item.name, modifier = Modifier.padding(12.dp),fontSize = 16.sp,fontWeight = FontWeight.Bold)
        Text(text = item.quantity.toString(), modifier = Modifier.padding(12.dp),fontSize = 16.sp,fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.padding(18.dp)) {
            IconButton(onClick = {onDeleteClick()}, modifier = Modifier.size(24.dp)) {
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }
        }
    }
}

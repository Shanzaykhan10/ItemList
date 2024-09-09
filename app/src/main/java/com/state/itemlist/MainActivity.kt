package com.state.itemlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.String.format

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NameList(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
        }
    }
}

@Composable
fun NameList(modifier: Modifier) {
    var name by remember { mutableStateOf(value = "") }
    val nameNumberList = remember { mutableStateListOf<Pair<String,String>>()}
    var empty by remember { mutableStateOf(value = false) }
    var number by remember { mutableStateOf(value = "") }

    Column {
        Box(modifier = Modifier
            .background(
                color = Color(0xFF407D91)
            )
            .padding(top = 30.dp, bottom = 16.dp, end = 16.dp, start = 16.dp)
            .fillMaxWidth()
        )
        {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {

                 Text(text = "Name", fontSize = 20.sp,
                     color = Color.White,fontWeight = FontWeight.Bold
                 , modifier = Modifier.padding(start = 8.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeholder = {
                            Text(
                                text = "Enter name here", fontSize = 20.sp
                            )
                        }, colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        isError = empty
                    )
                Text(text = "Number", fontSize = 20.sp,
                    color = Color.White,fontWeight = FontWeight.Bold
                    , modifier = Modifier.padding(start = 8.dp))
                    OutlinedTextField(
                        value = number,
                        onValueChange = {
                            number = it
                        },
                        placeholder = {
                            Text(
                                text = "Enter Number here", fontSize = 20.sp
                            )
                        }, colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        isError = empty
                    )
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    Button(
                        onClick = {
                            if (name.isNotEmpty() && number.isNotEmpty()) {
                                empty = false
                                nameNumberList.add(Pair(name, number))
                                name = ""
                                number = ""
                            }
                            else empty = true
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF909BAB))
                    ) {
                        Text(
                            text = "ADD", color = Color.White, fontSize = 20.sp
                        )
                    }



            }
        }
            Spacer(modifier = Modifier.height(35.dp))
        Box(modifier = Modifier.padding(start = 5.dp)
            .shadow(2.dp, shape = RoundedCornerShape(5.dp))
            .background(
            color = Color(0xFF909BAB)
                )
        ) {
            Text(text = "Contact List",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 40.dp, end = 40.dp)
            )
        }
            LazyColumn {
                items(count = nameNumberList.size) {
                        index ->  NameItems(index= index , modifier = Modifier,name = nameNumberList[index])
                }
            }

    }

}

@Preview
@Composable
fun ListPreview() {
    Scaffold {
        innerPadding ->
        NameList(modifier = Modifier
            .padding(innerPadding)
        )
    }
}

@Composable
fun NameItems( index : Int ,modifier: Modifier,name: Pair<String,String>) {
    Spacer(modifier = Modifier.padding(5.dp))
    Row( modifier = Modifier
        .background(
            color = if (index % 2 == 0) Color(0xFF407D91) else Color(0xFF909BAB)
        )
        .fillMaxSize()
        .padding(
            bottom = 15.dp,
            top = 15.dp,
            start = 8.dp,
        )) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Account Circle",
            tint = Color.White,
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.padding(17.dp))
        Column {
            Text(text =  name.first,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(top = 14.dp)
            )
            Text(text =  name.second
                ,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
    HorizontalDivider(color = Color.Black)
}


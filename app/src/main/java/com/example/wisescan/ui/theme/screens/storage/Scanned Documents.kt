package com.example.wisescan.ui.theme.screens.storage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.STORAGE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannedDocuments(navController : NavHostController){
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ){
        TopAppBar(
            title = { Text(text = "SCANNED DOCUMENTS", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(STORAGE)
                })
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }

            },
        )
        Text(text = "There is no data at the moment.Upload data to view.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.SansSerif)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Download")
        }
        Divider()
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Upload")
            }

        }


    }

}
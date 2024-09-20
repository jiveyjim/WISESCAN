package com.example.wisescan.ui.theme.screens.camera

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.navigation.CAPTURE_SCREEN
import com.example.wisescan.navigation.DASHBOARD

@Composable
fun ScannedAiAnalysis(navController: NavHostController){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Text(text = "Important Notice",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif)
        Divider()
        Text(text = "We value your privacy and want to be transparent about how your data will be used. Our AI analysis feature will utilize your personal data to provide tailored, individual analysis and insights. This includes ,converting your data to computer based fonts, examining your data to offer personalized recommendations and improvements based on your specific content. Additionally, for broader and more accurate insights, we may aggregate your data with information from other users for collective analysis. This helps us enhance the overall performance of our AI algorithms and improve our service for everyone.\n" +
                "\n" +
                "Please note that your personal data will be processed and converted into actionable insights in a secure manner, adhering to our strict privacy policies. We will not share your individual data with third parties without your consent.\n" +
                "\n" +
                "By continuing, you consent to the use of your data for these purposes. If you have any concerns or do not wish to proceed, please do not continue.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.SansSerif
            )
        Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Continue")

        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate(DASHBOARD) }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Cancel")

        }

    }
}


import android.app.Activity

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wisescan.AppScaffold
import com.example.wisescan.R
import com.example.wisescan.navigation.CAREER_PILOT
import com.example.wisescan.navigation.GENERAL_ANALYSIS
import com.example.wisescan.navigation.PERSONALIZED_ANALYSIS
import com.example.wisescan.navigation.PERSONALIZED_CONTENT
import com.example.wisescan.navigation.SETTINGS
import com.example.wisescan.navigation.SOCIALIZE
import com.example.wisescan.navigation.TRAIN_AI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val activity = LocalContext.current as? Activity
    var showExitDialog by remember { mutableStateOf(false) }
    val mContext = LocalContext.current

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Exit") },
            text = { Text("Are you sure you want to exit?") },
            confirmButton = {
                TextButton(onClick = { activity?.finish() }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("No")
                }
            }
        )
    }


    AppScaffold(title = "Dashboard", navController = navController){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 50.dp)
                .verticalScroll(rememberScrollState())


        ) {


                TopAppBar(
                    title = {
                        Text(
                            text = "WISE SCAN",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue),
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(SETTINGS) }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { val shareIntent=Intent(Intent.ACTION_SEND)
                            shareIntent.type="text/plain"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool Ai APP to enhance your learning.")
                            mContext.startActivity(Intent.createChooser(shareIntent, "Share"))}) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.White
                            )
                        }
                    }
                )



            Card(onClick = { navController.navigate(PERSONALIZED_ANALYSIS)}) {
                Box {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.personalanalysis),
                            contentDescription = "Personalized Analysis",
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Personalized Analysis",
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(onClick = { navController.navigate(PERSONALIZED_CONTENT) }) {
                Box {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.personalizedcontent),
                            contentDescription = "Personalized Content",
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(220.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Personalized Content",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {

                Row(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        onClick = { navController.navigate(GENERAL_ANALYSIS)},
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.collectiveanalysis),
                                contentDescription = "Collective Analysis",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Collective Analysis",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }

                    Card(
                        onClick = { navController.navigate(SOCIALIZE) },
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.socialize),
                                contentDescription = "Socialize",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Personalized Group",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }
                }


                Row(
                    modifier = Modifier
                        .height(170.dp) // Adjust height to match the first row
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add vertical padding between rows
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        onClick = { navController.navigate(CAREER_PILOT) },
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.careerguidance),
                                contentDescription = "Career Pilot",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Career Pilot",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }

                    Card(
                        onClick = { navController.navigate(TRAIN_AI) },
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.trainai),
                                contentDescription = "Train & Earn",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Train & Earn",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }
                }
            }



        }
    }
}





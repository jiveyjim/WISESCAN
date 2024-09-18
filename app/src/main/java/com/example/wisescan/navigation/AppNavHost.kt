import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wisescan.ThemeViewModel
import com.example.wisescan.navigation.ABOUT
import com.example.wisescan.navigation.ACCOUNT_SETTINGS
import com.example.wisescan.navigation.CAREER_PILOT
import com.example.wisescan.navigation.COLLECTIVE_ANALYSIS
import com.example.wisescan.navigation.LOGIN
import com.example.wisescan.navigation.DASHBOARD
import com.example.wisescan.navigation.FORGOT_PASSWORD
import com.example.wisescan.navigation.LANGUAGE_SELECT
import com.example.wisescan.navigation.PERSONALIZED_ANALYSIS
import com.example.wisescan.navigation.PERSONALIZED_CONTENT
import com.example.wisescan.navigation.SETTINGS
import com.example.wisescan.navigation.SIGNUP
import com.example.wisescan.navigation.SOCIALIZE
import com.example.wisescan.navigation.STORAGE
import com.example.wisescan.navigation.TRAIN_AI
import com.example.wisescan.ui.theme.screens.authentication.ForgotPasswordScreen
import com.example.wisescan.ui.theme.screens.careerpilot.CareerPilotScreen
import com.example.wisescan.ui.theme.screens.collectiveanalysis.CollectiveAnalysisScreen
import com.example.wisescan.ui.theme.screens.personalizedanalysis.PersonalizedAnalysisScreen
import com.example.wisescan.ui.theme.screens.personalizedcontent.PersonalizedContentScreen
import com.example.wisescan.ui.theme.screens.settings.AboutScreen
import com.example.wisescan.ui.theme.screens.settings.AccountSettingsScreen
import com.example.wisescan.ui.theme.screens.settings.LanguageSelectScreen
import com.example.wisescan.ui.theme.screens.settings.SettingsScreen
import com.example.wisescan.ui.theme.screens.socialize.SocializeScreen
import com.example.wisescan.ui.theme.screens.storage.StorageScreen
import com.example.wisescan.ui.theme.screens.trainai.TrainAiScreen
import com.example.wisescan.viewmodel.AccountViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    themeViewModel: ThemeViewModel
) {
    val recognizedText = remember { mutableStateOf("") }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(SIGNUP) {
            SignUpScreen(navController = navController)
        }
        composable(LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(DASHBOARD) {
            DashboardScreen(navController = navController)
        }
        composable(FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(SETTINGS) {
            SettingsScreen(navController = navController, themeViewModel = themeViewModel)
        }
        composable(ACCOUNT_SETTINGS) {
            val accountViewModel: AccountViewModel = viewModel()
            AccountSettingsScreen(navController = navController, viewModel = accountViewModel)
        }
        composable(ABOUT) {
            AboutScreen(navController = navController, themeViewModel = themeViewModel)
        }
        composable(LANGUAGE_SELECT) {
            LanguageSelectScreen(navController = navController, context = LocalContext.current)
        }
        composable(PERSONALIZED_ANALYSIS) {
            PersonalizedAnalysisScreen(navController = navController)
        }
        composable(PERSONALIZED_CONTENT) {
            PersonalizedContentScreen(navController = navController)
        }
        composable(SOCIALIZE) {
            SocializeScreen(navController = navController)
        }
        composable(TRAIN_AI) {
            TrainAiScreen(navController = navController)
        }
        composable(COLLECTIVE_ANALYSIS) {
            CollectiveAnalysisScreen(navController = navController)
        }
        composable(CAREER_PILOT) {
            CareerPilotScreen(navController = navController)
        }
        composable(STORAGE) {
            StorageScreen(navController = navController)
        }
    }
}

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.navigation.compose.rememberNavController
import data.getDatabaseBuilder
import data.getRoomDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navHostController = rememberNavController()
    MaterialTheme {
        Scaffold {
            NavigationHost(navHostController)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

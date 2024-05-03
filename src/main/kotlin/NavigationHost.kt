import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import customers.presentation.CustomersScreen

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.CUSTOMERS.name
    ){
        composable(route = Screens.CUSTOMERS.name){
            CustomersScreen(navController = navController)
        }
    }
}
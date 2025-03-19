package com.problemsolver.feature.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.problemsolver.core.data.model.User
import com.problemsolver.core.data.model.userEmpty
import com.problemsolver.core.ui.LoadingAnimation
import com.problemsolver.core.utils.DATE_MONTH_YEAR_FORMAT
import com.problemsolver.core.utils.ISO_FORMAT
import com.problemsolver.core.utils.formatDate

@Composable
fun UserMain(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<UserViewModel>()

    NavHost(
        navController,
        startDestination = "user_list",
        modifier = modifier
    ) {
        composable("user_list") { UserListScreen(navController, viewModel) }
        composable("user_detail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            UserDetailScreen(userId, viewModel)
        }
    }
}

@Composable
fun UserListScreen(navController: NavController, userViewModel: UserViewModel) {
    val state by userViewModel.users.collectAsState()

    when (state) {
        is UserUiState.Loading, UserUiState.Empty,
        is UserUiState.Error -> {
            handleViewState(state)
        }

        is UserUiState.Success -> {
            LazyColumn {
                val users = (state as UserUiState.Success).data
                items(users) {
                    UserItem(it) {
                        navController.navigate("user_detail/${it.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun handleViewState(state: UserUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is UserUiState.Loading -> {
                LoadingAnimation()
            }

            is UserUiState.Error -> {
                Text("Error")
            }

            is UserUiState.Empty -> {
                Text("Empty")
            }

            else -> {}
        }
    }
}

@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imagePainter = rememberAsyncImagePainter(user.avatar)
        Image(
            painter = imagePainter,
            contentDescription = "User Avatar",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = user.name, fontWeight = FontWeight.Bold)
            Text(text = "${user.city}, ${user.country}")
            Text(text = user.createdAt.formatDate(ISO_FORMAT, DATE_MONTH_YEAR_FORMAT))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(userId: String, viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState(userEmpty())
    LaunchedEffect(userId) {
        viewModel.fetchUser(userId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(user.name) }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imagePainter = rememberAsyncImagePainter(user.avatar)
            Image(
                painter = imagePainter,
                contentDescription = "User Avatar",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${user.name}", fontWeight = FontWeight.Bold)
            Text(text = "City: ${user.city}")
            Text(text = "Country: ${user.country}")
            Text(text = "Street: ${user.street}, No. ${user.addressNo}")
            Text(text = "ZIP Code: ${user.zipCode}")
        }
    }
}
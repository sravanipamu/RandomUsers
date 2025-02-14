package com.sravani.randomusers.ui.userlist


import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.ui.base.HorizontalUserItem
import com.sravani.randomusers.ui.base.Route
import com.sravani.randomusers.ui.base.ShowError
import com.sravani.randomusers.ui.base.ShowLoading
import com.sravani.randomusers.ui.theme.DarkPrimaryColor
import com.sravani.randomusers.utils.AppConstants
import com.sravani.randomusers.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = AppConstants.APP_NAME,
                    fontSize = TextUnit.Unspecified,
                    color = Color.White
                )
            }, modifier = Modifier.shadow(2.dp), colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = DarkPrimaryColor
            )
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            UserListScreen(navController)
        }
    }
}

@Composable
fun UserListScreen(navController: NavController) {
    var viewModel: UserListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var resultNumber by remember {
        mutableStateOf<Int>(0)
    }

    SearchBox {
        resultNumber = it
        viewModel.fetchUserList(resultNumber)
    }
    when (uiState) {
        is UiState.Error -> {
            val errorState = uiState as UiState.Error
            ShowError(text = errorState.message)
        }

        is UiState.Success -> {
            val successState = uiState as UiState.Success
            UserItemList(userList = successState.data, navController)
        }

        UiState.Loading -> {
            ShowLoading()
        }

        else -> {
            // Handle any other states if necessary
        }
    }
}

@Composable
fun UserItemList(userList: List<User>, navController: NavController) {


    Column {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 10.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
        ) {
            items(userList.size) { index ->
                HorizontalUserItem(
                    userList.get(index).name.first + " " + userList.get(index).name.last,
                    userList.get(index).picture.medium,
                    userList.get(index).location
                ) {
                    val userJson = Gson().toJson(userList.get(index))
                    val encodedUserJson = Uri.encode(userJson)
                    navController.navigate(Route.Details.createRoute(encodedUserJson))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBox(goResult: (Int) -> Unit) {
    var number by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = number,
        onValueChange = { input ->
            if (input.all { it.isDigit() }) {
                number = input
                isError = false;
            }
        },
        placeholder = { Text("Enter a number") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, // Show numeric keyboard
            imeAction = ImeAction.Go           // Show "Go" button
        ),
        keyboardActions = KeyboardActions(onGo = {
            if (number.isEmpty() || number.toInt() == 0) {
                isError = true
            } else {
                keyboardController?.hide()
                goResult(number.toInt())
            }
        }),
        singleLine = true
    )
    if (isError) {
        Text(
            text = "Enter valid number",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }

}


@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.littlelemoncapstone

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor

@Composable
fun Onboarding(){
    val sharedPreferences =
        LocalContext.current.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    var firstName by rememberSaveable{
        mutableStateOf("")
    }
    var lastName by rememberSaveable{
        mutableStateOf("")
    }
    var email by rememberSaveable{
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current

    var showSnackBar by remember {
        mutableStateOf(false)
    }

    var snackBarMessage by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier.width(200.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Let's get to know you",
            fontSize = 25.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .padding(top = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = { Text(text = "First Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.green,
                    focusedLabelColor = LittleLemonColor.green
                ),
                placeholder = { Text(text = "Tilly") },
                modifier = Modifier.padding(10.dp),
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = { Text(text = "Last Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.green,
                    focusedLabelColor = LittleLemonColor.green
                ),
                placeholder = { Text(text = "Smith") },
                modifier = Modifier.padding(10.dp),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LittleLemonColor.green,
                    focusedLabelColor = LittleLemonColor.green
                ),
                placeholder = { Text(text = "TillyS@gmail.com") },
                modifier = Modifier.padding(10.dp),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )
        }
        Button(
            onClick = {
                showSnackBar = true
                snackBarMessage = if (firstName.isBlank() ||
                    lastName.isBlank() ||
                    email.isBlank()
                ) {
                    "Registration unsuccessful. Please enter all data."
                } else {
                    sharedPreferences.edit().putString("firstName", firstName).apply()
                    sharedPreferences.edit().putString("lastName", lastName).apply()
                    sharedPreferences.edit().putString("email", email).apply()
                    sharedPreferences.edit().putBoolean("loggedIn", true).apply()
                    "Registration successful!"
                }
            },
            colors = ButtonDefaults.buttonColors(
                Color(0xFFf4ce14)
            ),
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp, bottom = 50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Register",
                color = Color.Black
            )
        }
    }

}
@Preview
@Composable
fun OnboardingPreview(){
    Onboarding()
}

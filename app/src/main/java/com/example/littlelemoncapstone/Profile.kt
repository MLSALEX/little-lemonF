package com.example.littlelemoncapstone

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavController){

    val sharedPreferences = LocalContext.current.getSharedPreferences(
        "UserData", Context.MODE_PRIVATE
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription ="Little Lemon logo",
            modifier = Modifier.width(200.dp)
                .padding(top =10.dp),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Top
        ) {
            androidx.compose.material3.Text("Personal information", fontSize = 25.sp,)

            Spacer(modifier = Modifier.height(20.dp))
            Text("First Name", fontSize = 16.sp)
            androidx.compose.material3.Text("${sharedPreferences.getString("firstName", "")} ",
                fontSize = 22.sp)

            Spacer(modifier = Modifier.height(15.dp))
            androidx.compose.material3.Text("Last Name", fontSize = 16.sp)
            androidx.compose.material3.Text("${sharedPreferences.getString("lastName", "")}",
                fontSize = 22.sp)

            Spacer(modifier = Modifier.height(15.dp))
            androidx.compose.material3.Text("Email", fontSize = 16.sp,)
            androidx.compose.material3.Text("${sharedPreferences.getString("email", "")}",
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Divider(modifier = Modifier.fillMaxWidth())
        }
        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp, bottom = 50.dp)
                .fillMaxWidth()
            ,
        ) {
            Text(text = "Log out", fontSize = 18.sp, color = Color.Black)
        }
    }
}
@Preview
@Composable
fun ProfileComposable(){
    Profile(rememberNavController())
}
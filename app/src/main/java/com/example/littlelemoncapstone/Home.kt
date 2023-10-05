
package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor

@Composable
fun Home(navController: NavController) {

    var searchPhrase by remember {
        mutableStateOf("")
    }


    val categoryList = mutableListOf<String>()
    categoryList.add("Starters")
    categoryList.add("Mains")
    categoryList.add("Desserts")
    categoryList.add("Drinks")
    categoryList.add("Remove Filter")

    var selectedCategory by remember {
        mutableStateOf("")
    }

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    var menuItems = if (searchPhrase.isNotEmpty()) {
        databaseMenuItems.filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        }
    } else {
        if (selectedCategory.isNotEmpty()) {
            databaseMenuItems.filter {
                it.category == selectedCategory
            }
        } else {
            databaseMenuItems
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // to create space between the two images
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)  // the weight for the box
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(200.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .width(50.dp)
                    .clickable { navController.navigate(Profile.route) },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LittleLemonColor.green)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Little Lemon",
                    color = LittleLemonColor.yellow,
                    fontSize = 40.sp,
                    letterSpacing = 1.5.sp
                )
                Row {
                    Column {
                        Text(
                            text = "Chicago",
                            color = LittleLemonColor.cloud,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            color = LittleLemonColor.cloud,
                            modifier = Modifier.width(200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .width(200.dp)
                            .aspectRatio(16f / 20f)  // set the aspectRatio to 16:20
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillBounds  // fill the bounds of the container
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = {
                        searchPhrase = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Enter search phrase") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LittleLemonColor.yellow
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            items(
                items = categoryList,
                itemContent = { category ->
                    Row() {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .background(color = LittleLemonColor.cloud)
                                .clickable {
                                    selectedCategory = category.lowercase()
                                    if (category == "Remove Filter") {
                                        selectedCategory = ""
                                    }
                                }
                                .padding(15.dp)
                        ) {
                            Text(text = category, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                                color = LittleLemonColor.green
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(10.dp))
        MenuItemsList(items = menuItems)
    }
}
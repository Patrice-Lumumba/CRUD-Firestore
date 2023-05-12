package com.example.crud_firestore.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crud_firestore.util.SharedViewModel
import com.example.crud_firestore.util.UserData

@Composable
fun GetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
){
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }

    val context = LocalContext.current

    //back button
    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back_button"
                )
            }

            //Get data

            Column(
                modifier = Modifier
                    .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //user ID
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        value = userID,
                        onValueChange = {
                            userID = it
                        },
                        label = {
                            Text(text = "UserID")
                        }
                    )

                    //GET USER BUTTON

                    Button(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .width(100.dp),
                        onClick = {
                            sharedViewModel.retrieveData(
                                userID = userID,
                                context = context
                            ){
                                data ->
                                name = data.name
                                profession = data.profession
                                age = data.age.toString()
                                ageInt = age.toInt()
                            }
                        }
                    ){
                        Text(text = "Get Data")
                    }
                }

                //name

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(text = "Name")
                    }
                )

                //Profession

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = profession,
                    onValueChange = {
                        profession = it
                    },
                    label = {
                        Text(text = "Profession")
                    }
                )

                //age

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = age,
                    onValueChange = {
                        name = it
                        if(age.isNotEmpty()){
                            ageInt = age.toInt()
                        }
                    },
                    label = {
                        Text(text = "Age")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                //Save button
                Button(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    onClick = {
                        val userData = UserData(
                            userID = userID,
                            name = name,
                            profession = profession,
                            age = ageInt
                        )

                        sharedViewModel.saveData(userData = userData, context = context)
                    }
                ){
                    Text(text = "Save")
                }

                //Delete button
                Button(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    onClick = {
                        sharedViewModel.deleteData(
                            userID = userID,
                            context = context,
                            navController = navController
                        )
                    }
                ){
                    Text(text = "Save")
                }

            }
        }
    }
}
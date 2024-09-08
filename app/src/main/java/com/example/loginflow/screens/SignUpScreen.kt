package com.example.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginflow.R
import com.example.loginflow.components.CheckboxComponent
import com.example.loginflow.components.HeadingTextComponent
import com.example.loginflow.components.MyTextField
import com.example.loginflow.components.NormalTextComponent
import com.example.loginflow.components.PasswordTextField
import com.example.loginflow.navigation.PostOfficeAppRouter
import com.example.loginflow.navigation.Screen

@Composable
fun SignUpScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            NormalTextComponent(value = stringResource(R.string.hey_there))

            HeadingTextComponent(value = stringResource(R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = stringResource(R.string.first_name),
                painterResources = painterResource(R.drawable.user)
            )

            MyTextField(
                labelValue = stringResource(R.string.last_name),
                painterResources = painterResource(R.drawable.user)
            )

            MyTextField(
                labelValue = stringResource(R.string.email),
                painterResources = painterResource(R.drawable.icons8_email_50)
            )

            PasswordTextField(
                labelValue = stringResource(R.string.password),
                painterResources = painterResource(R.drawable.icons8_password_50)
            )

            CheckboxComponent(value = stringResource(R.string.terms_and_condtion),
                onTextSelected = {
                    PostOfficeAppRouter.navigationTo(Screen.TermsAndConditionsScreen)
                })
        }
    }
}

@Preview
@Composable
fun DerfaultPreview() {
    SignUpScreen()
}
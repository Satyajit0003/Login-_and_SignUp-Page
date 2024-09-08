package com.example.loginflow.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen() {
    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
}

object PostOfficeAppRouter {
    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigationTo(destination : Screen){
        currentScreen.value = destination
    }
}
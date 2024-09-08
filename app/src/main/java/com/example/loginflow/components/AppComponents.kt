package com.example.loginflow.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflow.R
import com.example.loginflow.ui.theme.BgColor
import com.example.loginflow.ui.theme.GrayColor
import com.example.loginflow.ui.theme.Primary
import com.example.loginflow.ui.theme.componentShape

@Composable
fun NormalTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        text = value,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        text = value,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue:String, painterResources: Painter) {

    var textValue by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgColor)
            .clip(componentShape.small),
        label = {
            Text(text = labelValue)
        },
        value = textValue,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            textValue = it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResources,
                contentDescription = " "
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue:String, painterResources: Painter) {

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgColor)
            .clip(componentShape.small),
        label = {
            Text(text = labelValue)
        },
        value = password,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = {
            password = it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResources,
                contentDescription = " "
            )
        },
        trailingIcon = {
            val iconImage = if(passwordVisible) {
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            val description = if(passwordVisible){
                stringResource(R.string.hide_password)
            }else{
                stringResource(R.string.show_password)
            }

            IconButton(onClick = {passwordVisible = !passwordVisible}) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun CheckboxComponent(value:String, onTextSelected: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {

        var checkedState by remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                checkedState = !checkedState
            }
        )
        ClickableTextComponent(value = value, onTextSelected  )
    }
}

@Composable
fun ClickableTextComponent(value:String, onTextSelected : (String) -> Unit) {

    val initialString = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = " and "
    val termsAndCondtionsText = " Terms of Use"

    val annotatedString = buildAnnotatedString {
        append(initialString)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = termsAndCondtionsText, annotation = termsAndCondtionsText)
            append(termsAndCondtionsText)
        }

    }

    ClickableText(text = annotatedString, onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{$span}")

                    if(span.item == termsAndCondtionsText || span.item == privacyPolicyText){
                        onTextSelected(span.item)
                    }
                }
        }
    )
}


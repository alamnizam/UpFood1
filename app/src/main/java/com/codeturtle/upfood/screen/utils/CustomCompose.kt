package com.codeturtle.upfood.screen.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeturtle.upfood.R

@Composable
fun CustomTextField(label: String, hint: String, keyboardType: KeyboardType) {
    var value by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF121212)
            )
        )
        Spacer(modifier = Modifier.padding(3.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(10.dp),
                    ambientColor = Color.Blue,
                )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(10))
                    .focusRequester(focusRequester = focusRequester),
                value = value,
                onValueChange = { value = it },
                singleLine = true,
                placeholder = {
                    Text(
                        text = hint,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFD9D9D9)
                        )
                    )
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF129575),
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    cursorColor = Color(0xFF129575),
                ),

                trailingIcon = {
                    if(keyboardType == KeyboardType.Password){
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    }
                },

                shape = RoundedCornerShape(10.dp),
                visualTransformation =
                if (keyboardType == KeyboardType.Password) {
                    if(!passwordVisible){
                        PasswordVisualTransformation()
                    }else{
                        VisualTransformation.None
                    }
                } else {
                    VisualTransformation.None
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                )
            )
        }
    }

}

@Composable
fun CustomButton(buttonText: String,onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575)),
        shape = RoundedCornerShape(size = 10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = buttonText, style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),

                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Icon(Icons.Outlined.ArrowForward, contentDescription = "Arrow forward")
        }

    }
}

@Composable
fun CustomCheckBox(label: String, isChecked: MutableState<Boolean>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Card(
            modifier = Modifier.background(Color.Transparent),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.5.dp, color = Color(0xFFFF9C00))
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(if (isChecked.value) Color(0xFFFF9C00) else Color.White)
                    .clickable {
                        isChecked.value = !isChecked.value
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isChecked.value)
                    Icon(Icons.Filled.Check, contentDescription = "", tint = Color.White)
            }
        }
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = label,
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFF9C00),
            )
        )
    }
}

@Composable
fun GoogleFacebookSignUp(
    googleOnClick: () -> Unit,
    facebookOnClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .width(60.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            shape = RoundedCornerShape(size = 10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = googleOnClick
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = stringResource(R.string.google_icon)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Button(
            modifier = Modifier
                .width(60.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            shape = RoundedCornerShape(size = 10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            onClick = facebookOnClick
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = stringResource(R.string.facebook_icon),
            )
        }
    }
}

@Composable
fun CustomDoubleText(
    nonUnderlineText: String,
    underlineText: String,
    onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
            ClickableText(onClick = onClick, text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000)
                )
            ) {
                append(nonUnderlineText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFF9C00),
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(underlineText)
            }
        })
    }
}
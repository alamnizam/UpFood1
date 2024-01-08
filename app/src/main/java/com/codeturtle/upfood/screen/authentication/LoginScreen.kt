package com.codeturtle.upfood.screen.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeturtle.upfood.R
import com.codeturtle.upfood.screen.utils.CustomButton
import com.codeturtle.upfood.screen.utils.CustomDoubleText
import com.codeturtle.upfood.screen.utils.CustomTextField
import com.codeturtle.upfood.screen.utils.GoogleFacebookSignUp
import com.codeturtle.upfood.viewmodel.LoginViewModel

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onForgotClick: (Int) -> Unit = {},
    onSignUpClick: (Int) -> Unit = {},
    loginViewModel: LoginViewModel? = null
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            GreetingSection()
            FormSection(onLoginClick, onForgotClick)
            SignUpSection(onSignUpClick,loginViewModel)
        }
    }
}

@Composable
private fun GreetingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = "Hello,", style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = "Welcome Back!", style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF121212),
                )
            )
        }
    }
}

@Composable
private fun FormSection(
    onLogin: () -> Unit,
    onForgotPassword: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        CustomTextField(
            label = stringResource(R.string.email),
            hint = stringResource(R.string.enter_email),
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.padding(10.dp))
        CustomTextField(
            label = stringResource(R.string.password),
            hint = stringResource(R.string.enter_password),
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.padding(10.dp))
        ClickableText(
            text = AnnotatedString(
                text = stringResource(R.string.forgot_password)
            ),
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFF9C00),
                textAlign = TextAlign.Center,
            ),
            onClick = onForgotPassword
        )
        Spacer(modifier = Modifier.padding(20.dp))
        CustomButton(buttonText = stringResource(R.string.login), onClick = onLogin)
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun SignUpSection(
    onRegister: (Int) -> Unit,
    loginViewModel: LoginViewModel?,
) {
    OtherOptionForSignInText()
    Spacer(modifier = Modifier.padding(10.dp))
    GoogleFacebookSignUp(
        googleOnClick = { },
        facebookOnClick = {
            
        }
    )
    Spacer(modifier = Modifier.padding(10.dp))
    CustomDoubleText(
        nonUnderlineText = stringResource(id = R.string.don_t_have_an_account),
        underlineText = stringResource(R.string.register),
        onClick = onRegister
    )
}

@Composable
fun OtherOptionForSignInText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color(0xFFD9D9D9), modifier = Modifier
                .height(2.dp)
                .width(50.dp)
        )
        Text(
            text = "Or Sign in With", modifier = Modifier.padding(
                horizontal = 10.dp
            ), style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFFD9D9D9),

                )
        )
        Divider(
            color = Color(0xFFD9D9D9), modifier = Modifier
                .height(2.dp)
                .width(50.dp)
        )
    }
}
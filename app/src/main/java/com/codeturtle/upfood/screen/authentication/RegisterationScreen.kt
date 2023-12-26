package com.codeturtle.upfood.screen.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeturtle.upfood.R
import com.codeturtle.upfood.screen.utils.CustomButton
import com.codeturtle.upfood.screen.utils.CustomCheckBox
import com.codeturtle.upfood.screen.utils.CustomDoubleText
import com.codeturtle.upfood.screen.utils.CustomTextField
import com.codeturtle.upfood.screen.utils.GoogleFacebookSignUp

@Preview(showSystemUi = true)
@Composable
fun RegistrationScreen(
    onRegisterClick: () -> Unit = {},
    onLoginClick: (Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            GreetingSection()
            FormSection(onRegisterClick)
            LoginSection(onLoginClick)
        }
    }
}

@Composable
private fun GreetingSection() {
    Text(
        text = "Create an account",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
    )
    Text(
        text = "Let’s help you set up your account, \nit won’t take long.",
        style = TextStyle(
            fontSize = 11.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF121212),
        )
    )
}

@Composable
private fun FormSection(
    onRegister: () -> Unit
) {
    val isChecked = remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.padding(10.dp))
    CustomTextField(
        label = stringResource(R.string.name),
        hint = stringResource(R.string.enter_name),
        keyboardType = KeyboardType.Text
    )
    Spacer(modifier = Modifier.padding(10.dp))
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
    CustomTextField(
        label = stringResource(R.string.confirm_password),
        hint = stringResource(R.string.enter_confirm_password),
        keyboardType = KeyboardType.Password
    )
    Spacer(modifier = Modifier.padding(10.dp))
    CustomCheckBox(label = "Accept terms & Condition", isChecked = isChecked)
    Spacer(modifier = Modifier.padding(10.dp))
    CustomButton(buttonText = stringResource(R.string.registration), onClick = onRegister)
}

@Composable
private fun LoginSection(
    onLogin: (Int) -> Unit,
) {
    Spacer(modifier = Modifier.padding(10.dp))
    OtherOptionForSignInText()
    Spacer(modifier = Modifier.padding(10.dp))
    GoogleFacebookSignUp(
        googleOnClick = {  },
        facebookOnClick = {  }
    )
    Spacer(modifier = Modifier.padding(10.dp))
    CustomDoubleText(
        nonUnderlineText = stringResource(R.string.already_a_member),
        underlineText = stringResource(R.string.login),
        onClick = onLogin
    )
}
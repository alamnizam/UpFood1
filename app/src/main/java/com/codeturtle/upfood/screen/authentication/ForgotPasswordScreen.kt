package com.codeturtle.upfood.screen.authentication

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeturtle.upfood.R
import com.codeturtle.upfood.data.Resource
import com.codeturtle.upfood.naviagtion.AuthScreen
import com.codeturtle.upfood.screen.utils.CustomButton
import com.codeturtle.upfood.screen.utils.CustomDoubleText
import com.codeturtle.upfood.ui.theme.UpFoodTheme
import java.util.regex.Pattern

@Preview(
    name="light-mode",
    showBackground = true
)
@Preview(
    name="dark-mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ForgotPasswordPreview1() {
    UpFoodTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ){
            ForgotPasswordScreen(viewModel = null, navController = rememberNavController())
        }
    }

}


@Composable
fun ForgotPasswordScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    val forgotPasswordFlow = viewModel?.forgotPasswordFLow?.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            InfoSection()
            FormSection(viewModel)
            GotoLoginSection(navController)
        }
        val context = LocalContext.current
        forgotPasswordFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    Toast.makeText(context, "reset password link sent!!", Toast.LENGTH_SHORT).show()
                    navController.navigate(AuthScreen.Login.route)
                }
            }
        }
    }
}

@Composable
fun GotoLoginSection(navController: NavHostController) {
    Spacer(modifier = Modifier.padding(10.dp))
    CustomDoubleText(
        nonUnderlineText = stringResource(R.string.go_to),
        underlineText = stringResource(R.string.login),
        onClick = {
            navController.navigate(AuthScreen.SignUp.route)
        }
    )
}

@Composable
fun FormSection(viewModel: AuthViewModel?) {
    var isError by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var isEmailErrorVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.email),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
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
                    ),

                border = if (isError) {
                    BorderStroke(width = 2.dp, color = Color.Red)
                } else {
                    null
                },
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(10))
                        .focusRequester(focusRequester = focusRequester),
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.enter_email),
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF7C7878)
                            )
                        )
                    },

                    isError = isError,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF129575),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        cursorColor = Color(0xFF129575),
                    ),

                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }
            AnimatedVisibility(
                visible = isEmailErrorVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = emailError,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color.Red
                    )
                )
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))
        CustomButton(buttonText = stringResource(R.string.send), onClick = {
            val emailRegex = "^(.+)@(.+)$"
            val pattern: Pattern = Pattern.compile(emailRegex)
            val matcher = pattern.matcher(email)
            if (email.isEmpty()) {
                emailError = "This field cannot be empty"
                isError = true
                isEmailErrorVisible = true
            } else if (!matcher.matches()) {
                emailError = "Enter valid email"
                isError = true
                isEmailErrorVisible = true
            } else {
                emailError = ""
                isError = false
                isEmailErrorVisible = false
                viewModel?.forgotPassword(email)
            }
        })
    }
}

@Composable
fun InfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = "Forgot password!!", style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(600),
                )
            )
            Text(
                text = "Don't worry !", style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                )
            )
        }
    }
}

package com.codeturtle.upfood.screen.authentication

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codeturtle.upfood.R
import com.codeturtle.upfood.data.Resource
import com.codeturtle.upfood.naviagtion.AuthScreen
import com.codeturtle.upfood.naviagtion.Graph
import com.codeturtle.upfood.screen.utils.CustomButton
import com.codeturtle.upfood.screen.utils.CustomDoubleText
import com.codeturtle.upfood.screen.utils.GoogleFacebookSignUp
import java.util.regex.Pattern

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {
    LoginScreen(viewModel = null,navController = rememberNavController())
}

@Composable
fun LoginScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    val loginFlow = viewModel?.loginFlow?.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            GreetingSection()
            LoginFormSection(viewModel,navController)
            SignUpSection(navController)

        }
        loginFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, "${it.exception}", Toast.LENGTH_SHORT).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.popBackStack(
                            route = Graph.AUTHENTICATION,
                            inclusive = true
                        )
                        navController.navigate(Graph.HOME)
                    }
                }
            }
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
private fun LoginFormSection(
    viewModel: AuthViewModel?,
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var isEmailErrorVisible by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var isPasswordErrorVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }
    val charLimit = 8
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
                                color = Color(0xFFD9D9D9)
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
        Spacer(modifier = Modifier.padding(10.dp))
        Column {
            Text(
                text = stringResource(R.string.password),
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
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.enter_password),
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFFD9D9D9)
                            )
                        )
                    },

                    isError = isError,

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF129575),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        cursorColor = Color(0xFF129575),
                    ),

                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },

                    shape = RoundedCornerShape(10.dp),
                    visualTransformation =
                    if (!passwordVisible) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }
            AnimatedVisibility(
                visible = isPasswordErrorVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = passwordError,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = Color.Red
                    )
                )
            }
        }
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
            onClick = {
                navController.navigate(AuthScreen.Forgot.route)
            }
        )
        Spacer(modifier = Modifier.padding(20.dp))
        CustomButton(buttonText = stringResource(R.string.login), onClick = {
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
            }else if (password.isEmpty()) {
                passwordError = "This field cannot be empty"
                isError = true
                isPasswordErrorVisible = true
            }else if (password.length > charLimit) {
                passwordError = "password cannot less than 8 character"
                isError = true
                isPasswordErrorVisible = true
            } else {
                emailError = ""
                passwordError = ""
                isError = false
                isEmailErrorVisible = false
                isPasswordErrorVisible = false
                viewModel?.login(email, password)
            }
        })
        Spacer(modifier = Modifier.padding(10.dp))
    }
}


@Composable
private fun SignUpSection(navController: NavHostController) {
    OtherOptionForSignInText()
    Spacer(modifier = Modifier.padding(10.dp))
    GoogleFacebookSignUp(
        googleOnClick = { },
        facebookOnClick = { }
    )
    Spacer(modifier = Modifier.padding(10.dp))
    CustomDoubleText(
        nonUnderlineText = stringResource(id = R.string.don_t_have_an_account),
        underlineText = stringResource(R.string.register),
        onClick = {
            navController.navigate(AuthScreen.SignUp.route)
        }
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
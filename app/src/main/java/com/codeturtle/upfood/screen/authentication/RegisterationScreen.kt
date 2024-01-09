package com.codeturtle.upfood.screen.authentication

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun RegistrationPreview() {
    RegistrationScreen(viewModel = null, navController = rememberNavController())
}

@Composable
fun RegistrationScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    val signUpFLow = viewModel?.signUpFLow?.collectAsState()
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
            SignUpFormSection(viewModel)
            LoginSection(navController)
        }
        signUpFLow?.value?.let {
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
private fun SignUpFormSection(
    viewModel: AuthViewModel?
) {
    val isChecked = remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var isNameErrorVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var isEmailErrorVisible by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var isPasswordErrorVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var isConfirmPasswordErrorVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }
    val charLimit = 8
    val context = LocalContext.current
    Spacer(modifier = Modifier.padding(10.dp))
    Column {
        Text(
            text = stringResource(R.string.name),
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
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_name),
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
                    keyboardType = KeyboardType.Text
                )
            )
        }
        AnimatedVisibility(
            visible = isNameErrorVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = nameError,
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
    Column {
        Text(
            text = stringResource(R.string.confirm_password),
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
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_confirm_password),
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
            visible = isConfirmPasswordErrorVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = confirmPasswordError,
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
            text = "Accept terms & Condition",
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFFF9C00),
            )
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
    CustomButton(buttonText = stringResource(R.string.registration)) {
        val emailRegex = "^(.+)@(.+)$"
        val pattern: Pattern = Pattern.compile(emailRegex)
        val matcher = pattern.matcher(email)
        if(name.isEmpty()){
            nameError = "This field cannot be empty"
            isError = true
            isNameErrorVisible = true
        }else if (email.isEmpty()) {
            emailError = "This field cannot be empty"
            isError = true
            isEmailErrorVisible = true
        } else if (!matcher.matches()) {
            emailError = "Enter valid email"
            isError = true
            isEmailErrorVisible = true
        } else if (password.isEmpty()) {
            passwordError = "This field cannot be empty"
            isError = true
            isPasswordErrorVisible = true
        } else if (password.length > charLimit) {
            passwordError = "password cannot less than 8 character"
            isError = true
            isPasswordErrorVisible = true
        } else if (confirmPassword.isEmpty()) {
            confirmPasswordError = "This field cannot be empty"
            isError = true
            isConfirmPasswordErrorVisible = true
        } else if (password != confirmPassword) {
            confirmPasswordError = "password and confirm password must be same"
            isError = true
            isConfirmPasswordErrorVisible = true
        } else if (!isChecked.value) {
            Toast.makeText(context, "Please accept term and condition", Toast.LENGTH_SHORT).show()
        } else {
            nameError = ""
            emailError = ""
            passwordError = ""
            confirmPasswordError = ""
            isError = false
            isNameErrorVisible = false
            isEmailErrorVisible = false
            isPasswordErrorVisible = false
            isConfirmPasswordErrorVisible = false
            viewModel?.signUp(name,email, password)
        }
    }
}

@Composable
private fun LoginSection(navController: NavHostController) {
    Spacer(modifier = Modifier.padding(10.dp))
    OtherOptionForSignInText()
    Spacer(modifier = Modifier.padding(10.dp))
    GoogleFacebookSignUp(
        googleOnClick = { },
        facebookOnClick = { }
    )
    Spacer(modifier = Modifier.padding(10.dp))
    CustomDoubleText(
        nonUnderlineText = stringResource(R.string.already_a_member),
        underlineText = stringResource(R.string.login),
        onClick = {
            navController.navigate(AuthScreen.Login.route)
        }
    )
}
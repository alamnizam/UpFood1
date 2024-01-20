package com.codeturtle.upfood.screen.authentication

import android.content.res.Configuration
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import com.codeturtle.upfood.ui.theme.UpFoodTheme
import java.util.regex.Pattern

@Preview(
    name = "light-mode",
    showBackground = true
)
@Preview(
    name = "dark-mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun RegistrationPreview() {
    UpFoodTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            RegistrationScreen(viewModel = null, navController = rememberNavController())
        }
    }
}

@Composable
fun RegistrationScreen(
    viewModel: AuthViewModel? = hiltViewModel(),
    navController: NavHostController
) {
    val signUpFlow = viewModel?.signUpFlow?.collectAsState()
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
        val context = LocalContext.current
        signUpFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    Toast.makeText(context, "User register successfully!!", Toast.LENGTH_SHORT)
                        .show()
                    Toast.makeText(context, "Verification email sent!!", Toast.LENGTH_SHORT).show()
                    if (it.result.isEmailVerified) {
                        LaunchedEffect(Unit) {
                            navController.popBackStack(
                                route = Graph.AUTHENTICATION,
                                inclusive = true
                            )
                            navController.navigate(Graph.HOME)
                        }
                    } else {
                        Toast.makeText(context, "Please verify email!!", Toast.LENGTH_SHORT).show()
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
            color = MaterialTheme.colorScheme.onPrimary
        )
    )
    Text(
        text = "Let’s help you set up your account, \nit won’t take long.",
        style = TextStyle(
            fontSize = 11.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.onPrimary,
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
                color = MaterialTheme.colorScheme.onPrimary
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
                value = name,
                onValueChange = {
                    name = it
                    isNameErrorVisible = it.isEmpty()
                },
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

                isError = isNameErrorVisible,

                colors = OutlinedTextFieldDefaults.colors(
                    errorCursorColor = MaterialTheme.colorScheme.primary,
                    errorBorderColor = Color.Red,
                    errorTextColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    cursorColor = MaterialTheme.colorScheme.primary
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
                color = MaterialTheme.colorScheme.onPrimary
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
                value = email,
                onValueChange = {
                    email = it
                    isEmailErrorVisible = !isValidEmail(it)
                    emailError = if (!isValidEmail(it)) {
                        "Enter valid email"
                    } else {
                        ""
                    }
                },
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

                isError = isEmailErrorVisible,

                colors = OutlinedTextFieldDefaults.colors(
                    errorCursorColor = MaterialTheme.colorScheme.primary,
                    errorBorderColor = Color.Red,
                    errorTextColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    cursorColor = MaterialTheme.colorScheme.primary
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
                color = MaterialTheme.colorScheme.onPrimary
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
                value = password,
                onValueChange = {
                    password = it
                    isPasswordErrorVisible = it.length < charLimit
                    passwordError = if (it.length < charLimit) {
                        "password cannot less than 8 character"
                    } else {
                        ""
                    }

                },
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

                isError = isPasswordErrorVisible,

                colors = OutlinedTextFieldDefaults.colors(
                    errorBorderColor = Color.Red,
                    errorCursorColor = MaterialTheme.colorScheme.primary,
                    errorTextColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),

                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = description,
                            tint = MaterialTheme.colorScheme.primary
                        )
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
                color = MaterialTheme.colorScheme.onPrimary
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
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    isConfirmPasswordErrorVisible = password != it
                    confirmPasswordError = if (password != it) {
                        "password and confirm password must be same"
                    } else {
                        ""
                    }
                },
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

                isError = isConfirmPasswordErrorVisible,

                colors = OutlinedTextFieldDefaults.colors(
                    errorCursorColor = MaterialTheme.colorScheme.primary,
                    errorBorderColor = Color.Red,
                    errorTextColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),

                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = description,
                            tint = MaterialTheme.colorScheme.primary
                        )
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
            border = BorderStroke(1.5.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(if (isChecked.value) MaterialTheme.colorScheme.primary else Color.White)
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
                color = MaterialTheme.colorScheme.tertiary
            )
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
    CustomButton(buttonText = stringResource(R.string.registration)) {
        if (!isChecked.value) {
            Toast.makeText(context, "Please accept term and condition", Toast.LENGTH_SHORT).show()
        }
        if (name.isEmpty()) {
            isNameErrorVisible = true
            nameError = "This field cannot be empty"
        } else {
            isNameErrorVisible = false
            nameError = ""
        }
        if (email.isEmpty()) {
            isEmailErrorVisible = true
            emailError = "This field cannot be empty"
        }else{
            isEmailErrorVisible = false
            emailError = ""
        }
        if (password.isEmpty()) {
            isPasswordErrorVisible = true
            passwordError = "This field cannot be empty"
        }else{
            isPasswordErrorVisible = false
            passwordError = ""
        }
        if (confirmPassword.isEmpty()) {
            isConfirmPasswordErrorVisible = true
            confirmPasswordError = "This field cannot be empty"
        }else{
            isConfirmPasswordErrorVisible = false
            confirmPasswordError = ""
        }
        if (
            !isNameErrorVisible &&
            !isEmailErrorVisible &&
            !isPasswordErrorVisible &&
            !isConfirmPasswordErrorVisible &&
            isChecked.value
        ) {
            viewModel?.signUp(name, email, password)
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^(.+)@(.+)$"
    val pattern: Pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(email)
    return matcher.matches()
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
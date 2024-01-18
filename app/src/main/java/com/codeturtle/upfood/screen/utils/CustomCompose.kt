package com.codeturtle.upfood.screen.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeturtle.upfood.R

@Composable
fun CustomButton(buttonText: String,onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
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
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Icon(Icons.Outlined.ArrowForward, contentDescription = "Arrow forward")
        }

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
                    color = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                append(nonUnderlineText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(underlineText)
            }
        })
    }
}
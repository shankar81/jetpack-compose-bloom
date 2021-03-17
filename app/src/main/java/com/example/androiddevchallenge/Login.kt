package com.example.androiddevchallenge

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginContent()
                }
            }
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Heading(label = "Log in with email")
        LoginInput(
            placeholder = "Email address"
        )
        LoginInput(
            placeholder = "Password (8+ characters)",
            visualTransformation = PasswordVisualTransformation()
        )
        Text(
            text = "By clicking below, you agree to our Terms of Use and consent to our Privacy Policy",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
        PrimaryButton(label = "Log in") {}
    }
}

@Composable
fun LoginInput(
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground),
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            LoginContent()
        }
    }
}
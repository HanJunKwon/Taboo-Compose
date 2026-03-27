package com.kwon.taboo.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kwon.taboo.compose.designsystem.TabooBackground
import com.kwon.taboo.compose.designsystem.button.TabooButton
import com.kwon.taboo.compose.designsystem.button.TabooOutlineButton
import com.kwon.taboo.compose.designsystem.theme.TabooTheme
import com.kwon.taboo.compose.ui.theme.TabooComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TabooComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )

                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TabooTheme {
        TabooBackground {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )

                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") }
                )
                TabooOutlineButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = "Button") },
                    enabled = false
                )
            }
        }
    }
}
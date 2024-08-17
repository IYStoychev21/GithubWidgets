package com.example.githubwidgets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubwidgets.ui.theme.GitHubWidgetsTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubWidgetsTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoginButton()
                }
            }
        }
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    private fun handleIntent(intent: Intent) {
        val data: Uri? = intent.data
        if (data != null && data.scheme == "github-widgets" && data.host == "auth") {
            val token = data.getQueryParameter("token")
            handleToken(token)
        }
    }

    private fun handleToken(token: String?) {
        if(token != null) {
            println(token)
        }
    }
}

@Composable
fun LoginButton() {
    val context = LocalContext.current
    val authUrl: String = stringResource(R.string.auth_url)

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
            context.startActivity(intent)
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Login with GitHub", fontSize = 18.sp)
    }
}

@Preview
@Composable
fun LoginButtonPreview() {
    LoginButton()
}
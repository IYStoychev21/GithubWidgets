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
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleWidgetUpdates(this)

        CredentialManager.sharedPreferences = EncryptedSharedPreferences.create(
            this,
            "auth_preferences",
            MasterKey.Builder(this)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        if(CredentialManager.getToken() != null) {
            val profileIntent = Intent(this, ProfileActivity::class.java).apply {}
            startActivity(profileIntent)
            finish()
        }

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
        val token = data?.getQueryParameter("token")
        val refreshToken = data?.getQueryParameter("refresh_token")

        handleToken(token, refreshToken)
    }

    private fun handleToken(token: String?, refreshToken: String?) {
        if(token != null && refreshToken != null) {
            CredentialManager.storeToken(token)
            CredentialManager.storeRefreshToken(refreshToken)

            val profileIntent = Intent(this, ProfileActivity::class.java).apply {}

            startActivity(profileIntent)
            finish()
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
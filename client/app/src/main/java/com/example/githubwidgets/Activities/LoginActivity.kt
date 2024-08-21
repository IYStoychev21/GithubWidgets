package com.example.githubwidgets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubwidgets.Activities.ProfileActivity
import com.example.githubwidgets.Activities.ui.theme.GithubWidgetsTheme
import com.example.githubwidgets.Storage.UserStorage
import com.example.githubwidgets.Workers.scheduleNetworkWorker
import com.example.githubwidgets.Workers.scheduleWidgetUpdates

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CredentialManager.initCredentialPrefs(this)
        UserStorage.initPref(this)

        scheduleWidgetUpdates(this)
        scheduleNetworkWorker(this)

        if(CredentialManager.getCredential("token") != null) {
            val profileIntent = Intent(this, ProfileActivity::class.java).apply {  }
            startActivity(profileIntent)
            finish()
        }

        enableEdgeToEdge()
        setContent {
            GithubWidgetsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff0F0F0F)),
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
        intent.let { handleIntent(intent) }
    }

    private fun handleIntent(intent: Intent) {
        val data: Uri? = intent.data

        val token = data?.getQueryParameter("token")
        val refreshToken = data?.getQueryParameter("refresh_token")

        handleToken(token, refreshToken)
    }

    private fun handleToken(token: String?, refreshToken: String?) {
        if(token != null && refreshToken != null) {
            CredentialManager.storeCredential("token", token)
            CredentialManager.storeCredential("refresh_token", refreshToken)

            val profileIntent = Intent(this, ProfileActivity::class.java).apply {  }
            startActivity(profileIntent)
            finish()
        }
    }
}

@Composable
fun LoginButton() {
    val context = LocalContext.current

    Button(
        modifier = Modifier
            .padding(16.dp)
            .size(240.dp, 50.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff625B71),
            contentColor = Color(0xffffffff),
        ),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github-widgets-api-c9b3bdamdrg0hrg3.germanywestcentral-01.azurewebsites.net/auth/github"))
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.github_icon),
                contentDescription = "Github Icon",
                modifier = Modifier
                    .size(40.dp)
            )
            
            Spacer(modifier = Modifier.weight(10f))

            Text(
                text = "Login with Github",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
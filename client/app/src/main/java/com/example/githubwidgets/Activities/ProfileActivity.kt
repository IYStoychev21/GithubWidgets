package com.example.githubwidgets.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.glance.Button
import androidx.glance.appwidget.updateAll
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubwidgets.Activities.ui.theme.GithubWidgetsTheme
import com.example.githubwidgets.CredentialManager
import com.example.githubwidgets.LoginActivity
import com.example.githubwidgets.Storage.UserStorage
import com.example.githubwidgets.Widgets.ContributionWidget
import com.example.githubwidgets.Workers.manualNetworkWorkerUpdate
import com.example.githubwidgets.Workers.updateWidgetManually
import com.example.githubwidgets.apis.UserAPI
import com.example.githubwidgets.types.UserType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GithubWidgetsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff0F0F0F)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 40.dp, vertical = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfileCardFetch()
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Widgets available in widget your panel",
                            color = Color(0xffffffff),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileCardFetch() {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var user by remember { mutableStateOf<UserType?>(null)}

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            try {
                user = UserAPI.getUserProfile()
            } catch (e: Exception) {
                val loginIntent = Intent(context, LoginActivity::class.java).apply { }
                context.startActivity(loginIntent)
                CredentialManager.clearCredential("token")
                
                e.printStackTrace()
            }
        }
    }
    
    if(user != null) {
        UserStorage.storeValue("username", user!!.username)
        updateWidgetManually(context)

        ProfileCard(user!!)
    } else {
        Text(
            text = "Fetching user...",
            color = Color(0xffffffff)
        )
    }
}

@Composable
fun ProfileCard(user: UserType) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF625B71), RoundedCornerShape(10.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatar)
                .crossfade(true)
                .build(),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .width(100.dp)
                .graphicsLayer {
                    this.shape = CircleShape
                    this.clip = true
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = user.name,
                color = Color(0xffffffff),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = user.username,
                color = Color(0xff999999),
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = user.email,
                color = Color(0xffffffff),
                fontSize = 15.sp
            )

            Text(
                text = user.company,
                color = Color(0xff999999),
                fontSize = 15.sp
            )
        }
    }
}
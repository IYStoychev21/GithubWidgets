package com.example.githubwidgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubwidgets.ui.theme.GitHubWidgetsTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubwidgets.OkHttpClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import org.json.JSONObject

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val token = intent.getStringExtra("TOKEN")
        
        enableEdgeToEdge()
        setContent {
            GitHubWidgetsTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF090909)),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 40.dp, vertical = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GetProfile(token = token)
                    }
                }
            }
        }
    }
}

@Composable
fun GetProfile(token: String?) {
    if (token == null)
        return

    val scope = rememberCoroutineScope()
    var userProfile by remember { mutableStateOf<ProfileData?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = OkHttpClientInstance.getUserProfile(token)
                val jsonResponse = JSONObject(response ?: "")
                userProfile = ProfileData(
                    username = jsonResponse.getString("username"),
                    email = jsonResponse.getString("email"),
                    name = jsonResponse.getString("name"),
                    avatar = jsonResponse.getString("avatar"),
                    company = jsonResponse.getString("company")
                )
            } catch (e: Exception) {
                error = "Failed to load user data"
                e.printStackTrace()
            }
        }
    }

    if(userProfile != null) {
        ProfileCard(userProfile!!)
    }else if (error != null) {
        Text(text = error!!, color = Color(0xffffffff))
    } else {
        Text(text = "Loading...", color = Color(0xffffffff))
    }
}

@Composable
fun ProfileCard(userProfile: ProfileData) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF625B71), RoundedCornerShape(10.dp))
            .padding(15.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(userProfile.avatar)
                .crossfade(true)
                .build(),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .width(80.dp)
                .graphicsLayer {
                    this.shape = CircleShape
                    this.clip = true
                }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userProfile.name,
                    color = Color(0xffffffff),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = userProfile.username,
                    color = Color(0xff999999),
                    fontSize = 15.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userProfile.email,
                    color = Color(0xffffffff),
                    fontSize = 15.sp
                )

                Text(
                    text = userProfile.company,
                    color = Color(0xff999999),
                    fontSize = 15.sp
                )
            }
        }
    }
}

data class ProfileData(
    val email: String,
    val name: String,
    val avatar: String,
    val username: String,
    val company: String
)
package com.example.googleauth.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.googleauth.models.AuthViewModel

private val HeaderPurple = Color(0xFF534AB7)

// bottom edge dips flat at the sides and rises in the middle, like a shallow smile
class BottomRiseShape(private val riseHeight: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val risePx = with(density) { riseHeight.toPx() }
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height)
            quadraticBezierTo(
                size.width / 2f, size.height - risePx * 2f, // control point pulls the middle up
                size.width, size.height
            )
            lineTo(size.width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    val user = authViewModel.currentUser
    val context = LocalContext.current

    val headerHeight = 180.dp
    val riseHeight = 40.dp   // how far the curve's peak rises
    val avatarSize = 120.dp

    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                        .clip(BottomRiseShape(riseHeight))
                        .background(HeaderPurple)
                )

                // avatar centered on the peak of the curve, half above / half below it
                AsyncImage(
                    model = user?.photoUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = headerHeight - riseHeight - avatarSize / 2)
                        .size(avatarSize)
                        .clip(CircleShape)
                )
            }

            Column(
                modifier = Modifier.padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = user?.displayName ?: "there",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Button(
            onClick = {
                authViewModel.signOut(context)
                navController.navigate("signIn") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = HeaderPurple)
        ) {
            Text("Sign out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
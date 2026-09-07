package ph.edu.comteq.delpradojonneohylaboratoryexercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.runtime.saveable.rememberSaveable
import ph.edu.comteq.delpradojonneohylaboratoryexercise2.ui.theme.DelPradoJonNeohYLaboratoryExercise2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            DelPradoJonNeohYLaboratoryExercise2Theme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen(){
    //rememberSaveable survives screen rotation, remember does not
    var isFollowing by rememberSaveable {mutableStateOf(false)}
    // view count don't need to be saved, remember is fine
    var viewCount by remember { mutableIntStateOf(0)}

    // Root layout: Column stacks everything vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // light gray background
    ){
        // Cover photo
        Image(
            painter = painterResource(id = R.drawable.cover),
            contentDescription = "Cover photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop // fills the width without stretching/distorting
        )

        // Avatar + Name/Bio row, sits right below the cover
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 12.dp),
            verticalAlignment = Alignment.CenterVertically // keeps avatar and name/bio level with each other
        ) {
            // Avatar
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(90.dp)
                    .border(3.dp, Color.White, CircleShape) // white circular border
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Name and bio stacked beside the avatar
            Column {
                Text(
                    text = "Jon Neoh Y. Del Prado",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "BSIT Student : Mobile Developer",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        // Small gap before contact info
        Spacer(modifier = Modifier.height(12.dp))

        //Profile details section, left-aligned
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            // Contact info, email and phone side by side in one row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Email, contentDescription = "Email", tint = Color.Gray)
                Spacer(modifier = Modifier.width(6.dp))
                Text("delpradojonneoh1605@gmail.com", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.width(16.dp)) // gap between email and phone group

                Icon(Icons.Default.Phone, contentDescription = "Phone", tint = Color.Gray)
                Spacer(modifier = Modifier.width(6.dp))
                Text("+63 998 9022 469", style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(20.dp))

            //Buttons
            // Follow button color changes based on isFollowing state

            // Buttons left-aligned, Row doesn't fill max width and parent Column is left-aligned by default
            Row {
                // Follow button state hoisted to ProfileScreen, this just reads/toggles it
                FollowButton(
                    isFollowing = isFollowing,
                    onToggle = { isFollowing = !isFollowing }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50), // muted green
                        contentColor = Color.White
                    )
                ) {
                    Text("Message")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //Counter
            ProfileViewCounter(
                viewCount = viewCount,
                onIncrement = { viewCount++ }
            )
        }
    }
}
@Composable
fun FollowButton(
    isFollowing: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
){
    if(isFollowing){
        //Outlined Style when followed
        OutlinedButton(
            onClick = onToggle,
            modifier = modifier,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )
        ){
            Text("Following")
        }
    } else {
        //Normal styling
        Button(
            onClick = onToggle,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A6FA5),
                contentColor = Color.White
            ),
            modifier = modifier
        ){
            Text("Follow")
        }
    }
}

@Composable
fun ProfileViewCounter(
    viewCount: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Profile views: $viewCount",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(12.dp))
        Button(onClick = onIncrement) {
            Text("+1")
        }
    }
}

//Preview function
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    DelPradoJonNeohYLaboratoryExercise2Theme {
        ProfileScreen()
    }
}
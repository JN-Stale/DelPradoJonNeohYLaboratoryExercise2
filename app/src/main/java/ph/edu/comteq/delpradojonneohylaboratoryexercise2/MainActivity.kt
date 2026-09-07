package ph.edu.comteq.delpradojonneohylaboratoryexercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
    // Root layout: Column stacks everything vertically, centered
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        //Avatar
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Name and bio
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
        Spacer(modifier = Modifier.height(20.dp))

        //Contact info
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Email,
                contentDescription = "Email",
                tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("delpradojonneoh1605@gmail.com")
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Phone,
                contentDescription = "Phone",
                tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("+63 998 9022 469")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Buttons
        var isFollowing by remember {mutableStateOf(false)}
        //isFollowing state changes if followed or not

        Row{
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Message")
            }
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedButton(onClick = {isFollowing = !isFollowing}){
                Text(if (isFollowing) "Following" else "Follow")
            }
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
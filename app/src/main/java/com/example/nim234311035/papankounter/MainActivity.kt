package com.example.nim234311035.papankounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nim234311035.papankounter.ui.theme.PapanKounterTheme

class MainActivity : ComponentActivity() {
    private val viewModel: AngkaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PapanKounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PapanKounterApp(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PapanKounterApp(viewModel: AngkaViewModel, modifier: Modifier = Modifier) {
    val scoreTeamA by viewModel.scoreTeamA.collectAsState()
    val scoreTeamB by viewModel.scoreTeamB.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            //Headernya Dibagian ini
            Text(
                text = "Papan Score",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Bagian skor berada di tengah layar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TeamScore("Team A", scoreTeamA, viewModel::increaseScoreA, viewModel::decreaseScoreA)
                TeamScore("Team B", scoreTeamB, viewModel::increaseScoreB, viewModel::decreaseScoreB)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.resetScores() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("RESET", color = Color.White)
            }
        }
    }
}

@Composable
fun TeamScore(teamName: String, score: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(text = teamName, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = score.toString(), fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(
                onClick = onIncrease,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("+1", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onDecrease,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("-1", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    PapanKounterTheme {
        PapanKounterApp(AngkaViewModel())
    }
}
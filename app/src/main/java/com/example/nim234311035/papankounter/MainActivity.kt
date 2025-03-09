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
import androidx.compose.runtime.remember
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
    val gameTeamA by viewModel.gameTeamA.collectAsState()
    val gameTeamB by viewModel.gameTeamB.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Papan Skor Dipindah ke Atas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Papan Score",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Game: $gameTeamA - $gameTeamB",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Set: $scoreTeamA - $scoreTeamB",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Tampilan Skor Tim
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TeamScore("Team A", scoreTeamA, viewModel::increaseScoreA, viewModel::decreaseScoreA)
                TeamScore("Team B", scoreTeamB, viewModel::increaseScoreB, viewModel::decreaseScoreB)
            }
        }

        // Bagian Tombol Tetap di Bawah
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { viewModel.resetScores() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Reset Scores", color = Color.White)
                }
                Button(
                    onClick = { viewModel.resetGame() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Reset Game", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol End Game di Bawah
            Button(
                onClick = { viewModel.endCurrentGame() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                modifier = Modifier.padding(8.dp)
            ) {
                Text("End Game", color = Color.White)
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
    val dummyViewModel = remember { AngkaViewModel() } // ✅ Gunakan remember
    PapanKounterTheme {
        PapanKounterApp(viewModel = dummyViewModel)
    }
}
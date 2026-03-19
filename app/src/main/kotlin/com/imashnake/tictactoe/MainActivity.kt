package com.imashnake.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imashnake.tictactoe.ui.TicTacToe
import com.imashnake.tictactoe.ui.Turn
import com.imashnake.tictactoe.ui.theme.TicTacToeTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(WindowInsets.systemBars.asPaddingValues())
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var xos: ImmutableList<Boolean?> by remember {
        mutableStateOf(
            List(9) { null }.toImmutableList()
        )
    }

    var winner: Boolean? by remember { mutableStateOf(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = when (winner) {
                true -> "O is the winner!"
                false -> "X is the winner!"
                null -> "Play!"
            },
            color = MaterialTheme.colorScheme.onBackground
        )

        TicTacToe(
            ticTacToe = xos,
            onClick = { pos, turn ->
                if (winner == null) {
                    val updatedList = mutableListOf<Boolean?>()
                    repeat(9) {
                        if (it != pos) {
                            updatedList.add(xos[it])
                        } else {
                            updatedList.add(
                                when (turn) {
                                    Turn.X -> false
                                    Turn.O -> true
                                }
                            )
                        }
                    }
                    xos = updatedList.toImmutableList()

                    // check if there is a winner
                    // need to check 8 possibilities
                    repeat(3) { mod ->
                        
                    }
                }
            },
            modifier = modifier
        )

        Button(
            onClick = {
                xos = List(9) { null }.toImmutableList()
            }
        ) {
            Text("Clear")
        }
    }
}

package com.imashnake.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun TicTacToe(
    ticTacToe: ImmutableList<Boolean?>,
    onClick: (pos: Int, turn: Turn) -> Unit,
    modifier: Modifier = Modifier
) {
    var turn by remember { mutableStateOf(Turn.X) }

    Box(modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) { outerIndex ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(3) { innerIndex ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.Green)
                                .clickable {
                                    if (ticTacToe[(outerIndex + 1 + innerIndex * 3) - 1] == null) {
                                        // toggle turn
                                        turn = if (turn == Turn.X) Turn.O else Turn.X
                                        onClick(
                                            (outerIndex + 1 + innerIndex * 3) - 1,
                                            turn
                                        )
                                    }
                                }
                        ) {
                            Text(
                                text = when (ticTacToe[(outerIndex + 1 + innerIndex * 3) - 1]) {
                                    true -> "O"
                                    false -> "X"
                                    null -> ""
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Turn {
    X, O
}

package com.jefisu.calculatorcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Calculator(
    modifier: Modifier,
    viewModel: CalculatorViewModel,
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.h2.fontSize,
                color = Color.White,
                maxLines = 2
            )
            CalculatorRow(
                symbols = listOf("AC", "Del", "÷"),
                onAction = viewModel::onAction,
                lastSymbolAction = viewModel::onAction
            )
            CalculatorRow(
                symbols = listOf("7", "8", "9", "x"),
                onAction = viewModel::onAction,
                lastSymbolAction = viewModel::onAction
            )
            CalculatorRow(
                symbols = listOf("4", "5", "6", "-"),
                onAction = viewModel::onAction,
                lastSymbolAction = viewModel::onAction
            )
            CalculatorRow(
                symbols = listOf("1", "2", "3", "+"),
                onAction = viewModel::onAction,
                lastSymbolAction = viewModel::onAction
            )
            CalculatorRow(
                symbols = listOf("0", ".", "="),
                onAction = viewModel::onAction,
                lastSymbolAction = viewModel::onAction
            )
        }
    }
}
package com.jefisu.calculatorcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jefisu.calculatorcompose.ui.theme.MediumGray
import com.jefisu.calculatorcompose.ui.theme.Orange

@Composable
fun CalculatorRow(
    symbols: List<String>,
    backgroundColor: Color = MediumGray,
    onAction: (CalculatorAction) -> Unit,
    buttonSpacing: Dp = 8.dp,
    lastSymbol: String = symbols.last(),
    lastSymbolBackgroundColor: Color = Orange,
    lastSymbolAction: (CalculatorAction) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        symbols.forEach { symbol ->
            val symbolsSize = symbols.size == 3
            val isFirstSymbol = symbols.first() == symbol
            val value = if (symbolsSize && isFirstSymbol) 2f else 1f
            if (symbol != lastSymbol) {
                CalculatorButton(
                    symbol = symbol,
                    textColor = MaterialTheme.colors.onBackground,
                    backgroundColor = backgroundColor,
                    modifier = Modifier
                        .aspectRatio(value)
                        .weight(value),
                    onClick = {
                        val isNumber = symbol in (0..9).toList().toString()
                        if (isNumber) {
                            onAction(CalculatorAction.Number(symbol.toInt()))
                            return@CalculatorButton
                        }
                        val action = when (symbol) {
                            "AC" -> CalculatorAction.Clear
                            "Del" -> CalculatorAction.Delete
                            else -> CalculatorAction.Decimal
                        }
                        onAction(action)
                    }
                )
            }
        }
        CalculatorButton(
            symbol = lastSymbol,
            textColor = MaterialTheme.colors.background,
            backgroundColor = lastSymbolBackgroundColor,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            onClick = {
                val action = when (lastSymbol) {
                    "÷" -> CalculatorAction.Operation(CalculatorOperation.Divide)
                    "x" -> CalculatorAction.Operation(CalculatorOperation.Multiply)
                    "-" -> CalculatorAction.Operation(CalculatorOperation.Subtract)
                    "+" -> CalculatorAction.Operation(CalculatorOperation.Add)
                    else -> CalculatorAction.Calculate
                }
                lastSymbolAction(action)
            }
        )
    }
}
package com.example.keyboard_app.features.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keyboard_app.core.components.KeyView
import java.util.Locale

@Composable
fun KeyboardView() {
   var isCapital by remember { mutableStateOf(false) }
   val row1 = listOf("a",'b','c','d','e', 'f','g','h','i', 'j');

    Box(modifier = Modifier.height(300.dp).fillMaxWidth().background(MaterialTheme.colorScheme.primary)) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                row1.forEach {
                    KeyView(label = if(isCapital) it.toString().uppercase(Locale.getDefault()) else it.toString()  )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ){
                KeyView(label = "k")
                KeyView(label = "l")
                KeyView(label = "m")
                KeyView(label = "n")
                KeyView(label = "o")
                KeyView(label = "p")
                KeyView(label = "q")
                KeyView(label = "r")
                KeyView(label = "s")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                KeyView(label = "t")
                KeyView(label = "u")
                KeyView(label = "v")
                KeyView(label = "w")
                KeyView(label = "x")
                KeyView(label = "y")
                KeyView(label = "z", onClick = {
                    isCapital = !isCapital;
                })
            }
        }
    }
}



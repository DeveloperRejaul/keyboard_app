package com.example.keyboard_app.features.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keyboard_app.core.components.KeyView
import com.example.keyboard_app.core.ime.ComposeImeService
import com.example.keyboard_app.R
import java.util.Locale


@Composable
fun KeyboardView(keyboardViewModal: KeyboardViewModal = viewModel()) {
    val isCapital = keyboardViewModal.isCapital.collectAsState()
    val context = LocalContext.current
    val ime = context as? ComposeImeService



    val row1 = listOf("a",'b','c','d','e', 'f','g','h','i', 'j');
    val row2 = listOf("k",'l','m','n','o', 'p','q','r','s');
    val row3 = listOf("t",'u','v','w','x', 'y','z');


    Box(modifier = Modifier.height(300.dp).fillMaxWidth().background(MaterialTheme.colorScheme.primary)) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // ROW 01
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                row1.forEach {
                    KeyView(
                        label = if(isCapital.value) it.toString().uppercase(Locale.getDefault()) else it.toString()  ,
                        onClick = {
                            keyboardViewModal.type(ime , it.toString())
                        }
                    )
                }
            }
            // ROW 02
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            ){
                row2.forEach {
                    KeyView(
                        label = if(isCapital.value) it.toString().uppercase(Locale.getDefault()) else it.toString()  ,
                        onClick = {
                            keyboardViewModal.type(ime , it.toString())
                        }
                    )
                }
            }
            // ROW 03
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        keyboardViewModal.toggleKeyCapital()
                    },
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(3.dp)
                        )
                        .height(40.dp)
                ){
                    Icon(
                        painter = painterResource(id = if(isCapital.value) R.drawable.arrow_up else R.drawable.arrow_up_outline),
                        contentDescription = "Enter Key",
                        modifier = Modifier.size(27.dp)
                    )
                }
                row3.forEach {
                    KeyView(
                        label = if(isCapital.value) it.toString().uppercase(Locale.getDefault()) else it.toString() ,
                        onClick = {
                            keyboardViewModal.type(ime , it.toString())
                        }
                    )
                }

                IconButton(
                    onClick = {
                        keyboardViewModal.remove(ime)
                    },
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(3.dp)
                        )
                        .height(40.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Remove Key",
                        modifier = Modifier.size(27.dp)
                    )
                }
            }
        }
    }
}



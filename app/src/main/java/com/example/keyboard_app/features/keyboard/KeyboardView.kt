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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.keyboard_app.core.constance.KeyViewType
import com.example.keyboard_app.core.constance.Language
import com.example.keyboard_app.core.constance.Size


@Composable
fun KeyboardView(keyboardViewModal: KeyboardViewModal = viewModel()) {
    val context = LocalContext.current
    val ime = context as? ComposeImeService

    val row1 = keyboardViewModal.row2.collectAsState()
    val prvKeyViewType = keyboardViewModal.prvKeyViewType.collectAsState()
    val crrKeyViewType = keyboardViewModal.crrKeyViewType.collectAsState()
    val row2 = keyboardViewModal.row3.collectAsState()
    val row3 = keyboardViewModal.row4.collectAsState()


    Box(modifier = Modifier
        .height(Size.keyboardHeight)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)) {
        Column ( modifier = Modifier.fillMaxWidth() .padding(Size.keyboardHorizontalPadding, Size.keyboardVerticalPadding),verticalArrangement = Arrangement.spacedBy(Size.keyboardRowGap)) {
            // ROW 01
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Size.keyGap, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                row1.value.forEachIndexed { index, value ->
                    KeyView(
                        label = row1.value[index],
                        onClick = {
                            keyboardViewModal.type(ime , row1.value[index])
                        }
                    )
                }
            }
            // ROW 02
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            ){
                row2.value.forEachIndexed { index, value ->
                    KeyView(
                        label = row2.value[index] ,
                        onClick = {
                            keyboardViewModal.type(ime , row2.value[index])
                        }
                    )
                }
            }
            // ROW 03
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ){
                KeyView(
                    onClick = {
                        keyboardViewModal.arrowKey()
                    },
                    modifier = Modifier.weight(1f)
                ){
                    when(crrKeyViewType.value){
                        KeyViewType.ENGLISH_LOAR, KeyViewType.BANGLA_SCREEN_1 -> {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_up_outline),
                                contentDescription = "Uppercase and Lowercase ",
                                modifier = Modifier.size(27.dp)
                            )
                        }
                        KeyViewType.ENGLISH_UPPER,KeyViewType.BANGLA_SCREEN_2 -> {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_up),
                                contentDescription = "Uppercase and Lowercase ",
                                modifier = Modifier.size(27.dp)
                            )
                        }
                        KeyViewType.ENGLISH_NUMBER_AND_SEMBLE, KeyViewType.BANGLA_NUMBER_AND_SEMBLE -> {
                            Text("=\\<")
                        }
                        KeyViewType.ENGLISH_SEMBLE , KeyViewType.BANGLA_SEMBLE-> {
                            Text("?123")
                        }

                        else -> Unit
                    }

                }
                row3.value.forEachIndexed { index, value ->
                    KeyView(
                        label = row3.value[index],
                        onClick = {
                            keyboardViewModal.type(ime , row3.value[index])
                        }
                    )
                }

                KeyView(
                    onClick = {
                        keyboardViewModal.remove(ime)
                    },
                    onPressInterval = {
                        keyboardViewModal.remove(ime)
                    },
                    modifier = Modifier.weight(1f)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Remove Key",
                        modifier = Modifier.size(27.dp)
                    )
                }
            }

            // ROW 04
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                KeyView(
                    onClick = {
                        keyboardViewModal.numKey()
                    },
                    modifier = Modifier.weight(1f)
                ){
                    if(crrKeyViewType.value == KeyViewType.ENGLISH_UPPER || crrKeyViewType.value == KeyViewType.ENGLISH_LOAR) {
                        Text("?123")
                        return@KeyView
                    }
                    if(crrKeyViewType.value == KeyViewType.BANGLA_SCREEN_1 || crrKeyViewType.value == KeyViewType.BANGLA_SCREEN_2){
                        Text("?১২৩")
                        return@KeyView
                    }
                    when(prvKeyViewType.value) {
                        KeyViewType.ENGLISH_UPPER -> {
                            Text("ABC")
                        }
                        KeyViewType.ENGLISH_LOAR -> {
                            Text("abc")
                        }
                        KeyViewType.BANGLA_SCREEN_1 -> {
                            Text("bn1")
                        }
                        KeyViewType.BANGLA_SCREEN_2 -> {
                            Text("bn2")
                        }
                        else -> Unit
                    }

                }
                KeyView(
                    onClick = {},
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.emoji),
                        contentDescription = "emoji",
                        modifier = Modifier.size(27.dp)
                    )
                }
                KeyView(
                    label = ",",
                    onClick = {
                        keyboardViewModal.type(ime , ",")
                    }
                )

                KeyView(
                    label = "Space",
                    onClick = {
                        keyboardViewModal.type(ime , " ")
                    },
                    width = Size.SpaceKeyWidth()
                )

                when(crrKeyViewType.value){
                    KeyViewType.ENGLISH_LOAR, KeyViewType.ENGLISH_UPPER, KeyViewType.ENGLISH_SEMBLE, KeyViewType.ENGLISH_NUMBER_AND_SEMBLE -> {
                        KeyView(
                            label = "BN",
                            onClick = {
                                keyboardViewModal.toggleEnBn(Language.BN)
                            },
                        )
                    }
                    KeyViewType.BANGLA_SEMBLE,KeyViewType.BANGLA_SCREEN_1,KeyViewType.BANGLA_SCREEN_2,KeyViewType.BANGLA_NUMBER_AND_SEMBLE -> {
                        KeyView(
                            label = "EN",
                            onClick = {
                                keyboardViewModal.toggleEnBn(Language.EN)
                            },
                        )
                    }
                }

                KeyView(
                    onClick = {
                        keyboardViewModal.enter(ime)
                    },
                    onPressInterval = {
                        keyboardViewModal.enter(ime)
                    },
                    modifier = Modifier.weight(1f)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.enter),
                        contentDescription = "enter Key",
                        modifier = Modifier.size(27.dp)
                    )
                }
            }
        }
    }
}



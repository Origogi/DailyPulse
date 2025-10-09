package com.origogi.dailypulse.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.origogi.dailypulse.Platform

@Composable
fun AboutScreen(
    onUpButtonClick : () -> Unit
) {
    Column {
        Toolbar(
            onUpButtonClick = onUpButtonClick
        )
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onUpButtonClick: () -> Unit) {
    TopAppBar(
        title = {
            Text("About Devices")
        },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
private fun ContentView() {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        DeviceInfoRow(
            title = "OS Name",
            value = Platform.osName
        )
        DeviceInfoRow(
            title = "OS Version",
            value = Platform.osVersion
        )
        DeviceInfoRow(
            title = "Device Model",
            value = Platform.deviceModel
        )
    }
}

@Composable
private fun DeviceInfoRow(title: String, value: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider()
    }
}
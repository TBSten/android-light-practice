package me.tbsten.prac.light

import android.content.Context
import android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
import android.hardware.camera2.CameraManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LightTestScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LightTestScreen(
        onEnableLightClick = { enableLight(context) },
        onDisableLightClick = { disableLight(context) },
        onTestLight = { coroutineScope.launch { testLight(context) } },
    )
}

@Composable
private fun LightTestScreen(
    onEnableLightClick: () -> Unit,
    onDisableLightClick: () -> Unit,
    onTestLight: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onEnableLightClick) {
                    Text("enable light")
                }

                Button(onClick = onDisableLightClick) {
                    Text("disable light")
                }
            }

            Button(onClick = onTestLight) {
                Text("test light")
            }
        }
    }
}

private fun enableLight(context: Context) {
    val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    manager.cameraIdList.forEach { cameraId ->
        if (manager.isAvailableFlash(cameraId)) {
            manager.setTorchMode(cameraId, true)
        }
    }
}

private fun disableLight(context: Context) {
    val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    manager.cameraIdList.forEach { cameraId ->
        if (manager.isAvailableFlash(cameraId)) {
            manager.setTorchMode(cameraId, false)
        }
    }
}

private suspend fun testLight(context: Context) {
    repeat(3) {
        enableLight(context)
        delay(300)
        disableLight(context)
        delay(300)
    }
}


private fun CameraManager.isAvailableFlash(cameraId: String) =
    getCameraCharacteristics(cameraId).get(FLASH_INFO_AVAILABLE) == true

@Preview
@Composable
private fun LightTestScreenPreview() {
    LightTestScreen(
        onEnableLightClick = { },
        onDisableLightClick = { },
        onTestLight = { },
    )
}

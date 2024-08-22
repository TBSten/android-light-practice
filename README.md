# Android ライトチカチカのサンプル

## サンプル

| 画面                                                                                                                 | ライトの様子                                                                                                      |
|--------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| <img src="https://github.com/user-attachments/assets/dcdcccb5-435f-48e0-84a4-4d0db9abc393" alt="画面" width="300" /> | <video src="https://github.com/user-attachments/assets/fa50aee0-900e-47fa-9e1b-3df17686901a" width="300" /> | 

## コード

```kt
// ライトをつける
private fun enableLight(context: Context) {
    val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    manager.cameraIdList.forEach { cameraId ->
        if (manager.isAvailableFlash(cameraId)) {
            manager.setTorchMode(cameraId, true)
        }
    }
}

// ライトを消す
private fun disableLight(context: Context) {
    val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    manager.cameraIdList.forEach { cameraId ->
        if (manager.isAvailableFlash(cameraId)) {
            manager.setTorchMode(cameraId, false)
        }
    }
}


// 3回チカチカさせる
private suspend fun testLight(context: Context) {
    repeat(3) {
        enableLight(context)
        delay(300)
        disableLight(context)
        delay(300)
    }
}
```


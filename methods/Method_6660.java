public void stopRaiseToEarSensors(ChatActivity chatActivity,boolean fromChat){
  if (ignoreOnPause) {
    ignoreOnPause=false;
    return;
  }
  stopRecording(fromChat ? 2 : 0);
  if (!sensorsStarted || ignoreOnPause || accelerometerSensor == null && (gravitySensor == null || linearAcceleration == null) || proximitySensor == null || raiseChat != chatActivity) {
    return;
  }
  raiseChat=null;
  sensorsStarted=false;
  accelerometerVertical=false;
  proximityTouched=false;
  raiseToEarRecord=false;
  useFrontSpeaker=false;
  Utilities.globalQueue.postRunnable(() -> {
    if (linearSensor != null) {
      sensorManager.unregisterListener(MediaController.this,linearSensor);
    }
    if (gravitySensor != null) {
      sensorManager.unregisterListener(MediaController.this,gravitySensor);
    }
    if (accelerometerSensor != null) {
      sensorManager.unregisterListener(MediaController.this,accelerometerSensor);
    }
    sensorManager.unregisterListener(MediaController.this,proximitySensor);
  }
);
  if (proximityHasDifferentValues && proximityWakeLock != null && proximityWakeLock.isHeld()) {
    proximityWakeLock.release();
  }
}

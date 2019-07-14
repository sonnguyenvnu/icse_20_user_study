public void startRaiseToEarSensors(ChatActivity chatActivity){
  if (chatActivity == null || accelerometerSensor == null && (gravitySensor == null || linearAcceleration == null) || proximitySensor == null) {
    return;
  }
  raiseChat=chatActivity;
  if (!SharedConfig.raiseToSpeak && (playingMessageObject == null || !playingMessageObject.isVoice() && !playingMessageObject.isRoundVideo())) {
    return;
  }
  if (!sensorsStarted) {
    gravity[0]=gravity[1]=gravity[2]=0;
    linearAcceleration[0]=linearAcceleration[1]=linearAcceleration[2]=0;
    gravityFast[0]=gravityFast[1]=gravityFast[2]=0;
    lastTimestamp=0;
    previousAccValue=0;
    raisedToTop=0;
    raisedToTopSign=0;
    countLess=0;
    raisedToBack=0;
    Utilities.globalQueue.postRunnable(() -> {
      if (gravitySensor != null) {
        sensorManager.registerListener(MediaController.this,gravitySensor,30000);
      }
      if (linearSensor != null) {
        sensorManager.registerListener(MediaController.this,linearSensor,30000);
      }
      if (accelerometerSensor != null) {
        sensorManager.registerListener(MediaController.this,accelerometerSensor,30000);
      }
      sensorManager.registerListener(MediaController.this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
);
    sensorsStarted=true;
  }
}

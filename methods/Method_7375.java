protected void configureDeviceForCall(){
  needPlayEndSound=true;
  AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
  if (!USE_CONNECTION_SERVICE) {
    am.setMode(AudioManager.MODE_IN_COMMUNICATION);
    am.requestAudioFocus(this,AudioManager.STREAM_VOICE_CALL,AudioManager.AUDIOFOCUS_GAIN);
    if (isBluetoothHeadsetConnected() && hasEarpiece()) {
switch (audioRouteToSet) {
case AUDIO_ROUTE_BLUETOOTH:
        if (!bluetoothScoActive) {
          needSwitchToBluetoothAfterScoActivates=true;
          try {
            am.startBluetoothSco();
          }
 catch (          Throwable ignore) {
          }
        }
 else {
          am.setBluetoothScoOn(true);
          am.setSpeakerphoneOn(false);
        }
      break;
case AUDIO_ROUTE_EARPIECE:
    am.setBluetoothScoOn(false);
  am.setSpeakerphoneOn(false);
break;
case AUDIO_ROUTE_SPEAKER:
am.setBluetoothScoOn(false);
am.setSpeakerphoneOn(true);
break;
}
}
 else if (isBluetoothHeadsetConnected()) {
am.setBluetoothScoOn(speakerphoneStateToSet);
}
 else {
am.setSpeakerphoneOn(speakerphoneStateToSet);
}
}
updateOutputGainControlState();
audioConfigured=true;
SensorManager sm=(SensorManager)getSystemService(SENSOR_SERVICE);
Sensor proximity=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
try {
if (proximity != null) {
proximityWakelock=((PowerManager)getSystemService(Context.POWER_SERVICE)).newWakeLock(PROXIMITY_SCREEN_OFF_WAKE_LOCK,"telegram-voip-prx");
sm.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
}
}
 catch (Exception x) {
if (BuildVars.LOGS_ENABLED) {
FileLog.e("Error initializing proximity sensor",x);
}
}
}

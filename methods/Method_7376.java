@SuppressLint("NewApi") @Override public void onSensorChanged(SensorEvent event){
  if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
    AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
    if (isHeadsetPlugged || am.isSpeakerphoneOn() || (isBluetoothHeadsetConnected() && am.isBluetoothScoOn())) {
      return;
    }
    boolean newIsNear=event.values[0] < Math.min(event.sensor.getMaximumRange(),3);
    if (newIsNear != isProximityNear) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("proximity " + newIsNear);
      }
      isProximityNear=newIsNear;
      try {
        if (isProximityNear) {
          proximityWakelock.acquire();
        }
 else {
          proximityWakelock.release(1);
        }
      }
 catch (      Exception x) {
        FileLog.e(x);
      }
    }
  }
}

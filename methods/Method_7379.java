protected void updateBluetoothHeadsetState(boolean connected){
  if (connected == isBtHeadsetConnected)   return;
  if (BuildVars.LOGS_ENABLED)   FileLog.d("updateBluetoothHeadsetState: " + connected);
  isBtHeadsetConnected=connected;
  final AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
  if (connected && !isRinging() && currentState != 0) {
    if (bluetoothScoActive) {
      if (BuildVars.LOGS_ENABLED)       FileLog.d("SCO already active, setting audio routing");
      am.setSpeakerphoneOn(false);
      am.setBluetoothScoOn(true);
    }
 else {
      if (BuildVars.LOGS_ENABLED)       FileLog.d("startBluetoothSco");
      needSwitchToBluetoothAfterScoActivates=true;
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          try {
            am.startBluetoothSco();
          }
 catch (          Throwable ignore) {
          }
        }
      }
,500);
    }
  }
 else {
    bluetoothScoActive=false;
  }
  for (  StateListener l : stateListeners)   l.onAudioSettingsChanged();
}

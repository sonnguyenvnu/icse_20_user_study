public void updateOutputGainControlState(){
  if (controller == null || !controllerStarted)   return;
  if (!USE_CONNECTION_SERVICE) {
    AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
    controller.setAudioOutputGainControlEnabled(hasEarpiece() && !am.isSpeakerphoneOn() && !am.isBluetoothScoOn() && !isHeadsetPlugged);
    controller.setEchoCancellationStrength(isHeadsetPlugged || (hasEarpiece() && !am.isSpeakerphoneOn() && !am.isBluetoothScoOn() && !isHeadsetPlugged) ? 0 : 1);
  }
 else {
    boolean isEarpiece=systemCallConnection.getCallAudioState().getRoute() == CallAudioState.ROUTE_EARPIECE;
    controller.setAudioOutputGainControlEnabled(isEarpiece);
    controller.setEchoCancellationStrength(isEarpiece ? 0 : 1);
  }
}

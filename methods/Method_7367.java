public boolean isSpeakerphoneOn(){
  if (USE_CONNECTION_SERVICE && systemCallConnection != null && systemCallConnection.getCallAudioState() != null) {
    int route=systemCallConnection.getCallAudioState().getRoute();
    return hasEarpiece() ? route == CallAudioState.ROUTE_SPEAKER : route == CallAudioState.ROUTE_BLUETOOTH;
  }
 else   if (audioConfigured && !USE_CONNECTION_SERVICE) {
    AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
    return hasEarpiece() ? am.isSpeakerphoneOn() : am.isBluetoothScoOn();
  }
  return speakerphoneStateToSet;
}

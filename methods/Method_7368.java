public int getCurrentAudioRoute(){
  if (USE_CONNECTION_SERVICE) {
    if (systemCallConnection != null && systemCallConnection.getCallAudioState() != null) {
switch (systemCallConnection.getCallAudioState().getRoute()) {
case CallAudioState.ROUTE_BLUETOOTH:
        return AUDIO_ROUTE_BLUETOOTH;
case CallAudioState.ROUTE_EARPIECE:
case CallAudioState.ROUTE_WIRED_HEADSET:
      return AUDIO_ROUTE_EARPIECE;
case CallAudioState.ROUTE_SPEAKER:
    return AUDIO_ROUTE_SPEAKER;
}
}
return audioRouteToSet;
}
if (audioConfigured) {
AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
if (am.isBluetoothScoOn()) return AUDIO_ROUTE_BLUETOOTH;
 else if (am.isSpeakerphoneOn()) return AUDIO_ROUTE_SPEAKER;
 else return AUDIO_ROUTE_EARPIECE;
}
return audioRouteToSet;
}

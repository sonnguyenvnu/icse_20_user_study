public boolean isBluetoothHeadsetConnected(){
  if (USE_CONNECTION_SERVICE && systemCallConnection != null && systemCallConnection.getCallAudioState() != null)   return (systemCallConnection.getCallAudioState().getSupportedRouteMask() & CallAudioState.ROUTE_BLUETOOTH) != 0;
  return isBtHeadsetConnected;
}

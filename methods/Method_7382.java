protected void callFailed(int errorCode){
  try {
    throw new Exception("Call " + getCallID() + " failed with error code " + errorCode);
  }
 catch (  Exception x) {
    FileLog.e(x);
  }
  lastError=errorCode;
  dispatchStateChanged(STATE_FAILED);
  if (errorCode != VoIPController.ERROR_LOCALIZED && soundPool != null) {
    playingSound=true;
    soundPool.play(spFailedID,1,1,0,0,1);
    AndroidUtilities.runOnUIThread(afterSoundRunnable,1000);
  }
  if (USE_CONNECTION_SERVICE && systemCallConnection != null) {
    systemCallConnection.setDisconnected(new DisconnectCause(DisconnectCause.ERROR));
    systemCallConnection.destroy();
    systemCallConnection=null;
  }
  stopSelf();
}

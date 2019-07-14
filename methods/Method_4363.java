private void onKeyResponse(Object request,Object response){
  if (request != currentKeyRequest || !isOpen()) {
    return;
  }
  currentKeyRequest=null;
  if (response instanceof Exception) {
    onKeysError((Exception)response);
    return;
  }
  try {
    byte[] responseData=(byte[])response;
    if (mode == DefaultDrmSessionManager.MODE_RELEASE) {
      mediaDrm.provideKeyResponse(Util.castNonNull(offlineLicenseKeySetId),responseData);
      eventDispatcher.dispatch(DefaultDrmSessionEventListener::onDrmKeysRestored);
    }
 else {
      byte[] keySetId=mediaDrm.provideKeyResponse(sessionId,responseData);
      if ((mode == DefaultDrmSessionManager.MODE_DOWNLOAD || (mode == DefaultDrmSessionManager.MODE_PLAYBACK && offlineLicenseKeySetId != null)) && keySetId != null && keySetId.length != 0) {
        offlineLicenseKeySetId=keySetId;
      }
      state=STATE_OPENED_WITH_KEYS;
      eventDispatcher.dispatch(DefaultDrmSessionEventListener::onDrmKeysLoaded);
    }
  }
 catch (  Exception e) {
    onKeysError(e);
  }
}

private boolean shouldWaitForKeys(boolean bufferEncrypted) throws ExoPlaybackException {
  if (codecDrmSession == null || (!bufferEncrypted && playClearSamplesWithoutKeys)) {
    return false;
  }
  @DrmSession.State int drmSessionState=codecDrmSession.getState();
  if (drmSessionState == DrmSession.STATE_ERROR) {
    throw ExoPlaybackException.createForRenderer(codecDrmSession.getError(),getIndex());
  }
  return drmSessionState != DrmSession.STATE_OPENED_WITH_KEYS;
}

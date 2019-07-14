private boolean shouldWaitForKeys(boolean bufferEncrypted) throws ExoPlaybackException {
  if (decoderDrmSession == null || (!bufferEncrypted && playClearSamplesWithoutKeys)) {
    return false;
  }
  @DrmSession.State int drmSessionState=decoderDrmSession.getState();
  if (drmSessionState == DrmSession.STATE_ERROR) {
    throw ExoPlaybackException.createForRenderer(decoderDrmSession.getError(),getIndex());
  }
  return drmSessionState != DrmSession.STATE_OPENED_WITH_KEYS;
}

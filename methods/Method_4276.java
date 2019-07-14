private void onInputFormatChanged(Format newFormat) throws ExoPlaybackException {
  Format oldFormat=inputFormat;
  inputFormat=newFormat;
  boolean drmInitDataChanged=!Util.areEqual(inputFormat.drmInitData,oldFormat == null ? null : oldFormat.drmInitData);
  if (drmInitDataChanged) {
    if (inputFormat.drmInitData != null) {
      if (drmSessionManager == null) {
        throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"),getIndex());
      }
      DrmSession<ExoMediaCrypto> session=drmSessionManager.acquireSession(Looper.myLooper(),newFormat.drmInitData);
      if (session == decoderDrmSession || session == sourceDrmSession) {
        drmSessionManager.releaseSession(session);
      }
      setSourceDrmSession(session);
    }
 else {
      setSourceDrmSession(null);
    }
  }
  if (decoderReceivedBuffers) {
    decoderReinitializationState=REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM;
  }
 else {
    releaseDecoder();
    maybeInitDecoder();
    audioTrackNeedsConfigure=true;
  }
  encoderDelay=newFormat.encoderDelay;
  encoderPadding=newFormat.encoderPadding;
  eventDispatcher.inputFormatChanged(newFormat);
}

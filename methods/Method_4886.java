/** 
 * Called when a new format is read from the upstream  {@link MediaPeriod}.
 * @param newFormat The new format.
 * @throws ExoPlaybackException If an error occurs re-initializing the {@link MediaCodec}.
 */
protected void onInputFormatChanged(Format newFormat) throws ExoPlaybackException {
  Format oldFormat=inputFormat;
  inputFormat=newFormat;
  waitingForFirstSampleInFormat=true;
  boolean drmInitDataChanged=!Util.areEqual(newFormat.drmInitData,oldFormat == null ? null : oldFormat.drmInitData);
  if (drmInitDataChanged) {
    if (newFormat.drmInitData != null) {
      if (drmSessionManager == null) {
        throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"),getIndex());
      }
      DrmSession<FrameworkMediaCrypto> session=drmSessionManager.acquireSession(Looper.myLooper(),newFormat.drmInitData);
      if (session == sourceDrmSession || session == codecDrmSession) {
        drmSessionManager.releaseSession(session);
      }
      setSourceDrmSession(session);
    }
 else {
      setSourceDrmSession(null);
    }
  }
  if (codec == null) {
    maybeInitCodec();
    return;
  }
  if (sourceDrmSession != codecDrmSession) {
    drainAndReinitializeCodec();
  }
 else {
switch (canKeepCodec(codec,codecInfo,codecFormat,newFormat)) {
case KEEP_CODEC_RESULT_NO:
      drainAndReinitializeCodec();
    break;
case KEEP_CODEC_RESULT_YES_WITH_FLUSH:
  drainAndFlushCodec();
codecFormat=newFormat;
updateCodecOperatingRate();
break;
case KEEP_CODEC_RESULT_YES_WITH_RECONFIGURATION:
if (codecNeedsReconfigureWorkaround) {
drainAndReinitializeCodec();
}
 else {
codecReconfigured=true;
codecReconfigurationState=RECONFIGURATION_STATE_WRITE_PENDING;
codecNeedsAdaptationWorkaroundBuffer=codecAdaptationWorkaroundMode == ADAPTATION_WORKAROUND_MODE_ALWAYS || (codecAdaptationWorkaroundMode == ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION && newFormat.width == codecFormat.width && newFormat.height == codecFormat.height);
codecFormat=newFormat;
updateCodecOperatingRate();
}
break;
case KEEP_CODEC_RESULT_YES_WITHOUT_RECONFIGURATION:
codecFormat=newFormat;
updateCodecOperatingRate();
break;
default :
throw new IllegalStateException();
}
}
}

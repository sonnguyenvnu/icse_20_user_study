@Override protected boolean processOutputBuffer(long positionUs,long elapsedRealtimeUs,MediaCodec codec,ByteBuffer buffer,int bufferIndex,int bufferFlags,long bufferPresentationTimeUs,boolean shouldSkip,Format format) throws ExoPlaybackException {
  if (initialPositionUs == C.TIME_UNSET) {
    initialPositionUs=positionUs;
  }
  long presentationTimeUs=bufferPresentationTimeUs - outputStreamOffsetUs;
  if (shouldSkip) {
    skipOutputBuffer(codec,bufferIndex,presentationTimeUs);
    return true;
  }
  long earlyUs=bufferPresentationTimeUs - positionUs;
  if (surface == dummySurface) {
    if (isBufferLate(earlyUs)) {
      skipOutputBuffer(codec,bufferIndex,presentationTimeUs);
      return true;
    }
    return false;
  }
  long elapsedRealtimeNowUs=SystemClock.elapsedRealtime() * 1000;
  boolean isStarted=getState() == STATE_STARTED;
  if (!renderedFirstFrame || (isStarted && shouldForceRenderOutputBuffer(earlyUs,elapsedRealtimeNowUs - lastRenderTimeUs))) {
    long releaseTimeNs=System.nanoTime();
    notifyFrameMetadataListener(presentationTimeUs,releaseTimeNs,format);
    if (Util.SDK_INT >= 21) {
      renderOutputBufferV21(codec,bufferIndex,presentationTimeUs,releaseTimeNs);
    }
 else {
      renderOutputBuffer(codec,bufferIndex,presentationTimeUs);
    }
    return true;
  }
  if (!isStarted || positionUs == initialPositionUs) {
    return false;
  }
  long elapsedSinceStartOfLoopUs=elapsedRealtimeNowUs - elapsedRealtimeUs;
  earlyUs-=elapsedSinceStartOfLoopUs;
  long systemTimeNs=System.nanoTime();
  long unadjustedFrameReleaseTimeNs=systemTimeNs + (earlyUs * 1000);
  long adjustedReleaseTimeNs=frameReleaseTimeHelper.adjustReleaseTime(bufferPresentationTimeUs,unadjustedFrameReleaseTimeNs);
  earlyUs=(adjustedReleaseTimeNs - systemTimeNs) / 1000;
  if (shouldDropBuffersToKeyframe(earlyUs,elapsedRealtimeUs) && maybeDropBuffersToKeyframe(codec,bufferIndex,presentationTimeUs,positionUs)) {
    return false;
  }
 else   if (shouldDropOutputBuffer(earlyUs,elapsedRealtimeUs)) {
    dropOutputBuffer(codec,bufferIndex,presentationTimeUs);
    return true;
  }
  if (Util.SDK_INT >= 21) {
    if (earlyUs < 50000) {
      notifyFrameMetadataListener(presentationTimeUs,adjustedReleaseTimeNs,format);
      renderOutputBufferV21(codec,bufferIndex,presentationTimeUs,adjustedReleaseTimeNs);
      return true;
    }
  }
 else {
    if (earlyUs < 30000) {
      if (earlyUs > 11000) {
        try {
          Thread.sleep((earlyUs - 10000) / 1000);
        }
 catch (        InterruptedException e) {
          Thread.currentThread().interrupt();
          return false;
        }
      }
      notifyFrameMetadataListener(presentationTimeUs,adjustedReleaseTimeNs,format);
      renderOutputBuffer(codec,bufferIndex,presentationTimeUs);
      return true;
    }
  }
  return false;
}

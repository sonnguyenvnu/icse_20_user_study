/** 
 * {@link AudioTrack#getPlaybackHeadPosition()} returns a value intended to be interpreted as anunsigned 32 bit integer, which also wraps around periodically. This method returns the playback head position as a long that will only wrap around if the value exceeds  {@link Long#MAX_VALUE}(which in practice will never happen).
 * @return The playback head position, in frames.
 */
private long getPlaybackHeadPosition(){
  AudioTrack audioTrack=Assertions.checkNotNull(this.audioTrack);
  if (stopTimestampUs != C.TIME_UNSET) {
    long elapsedTimeSinceStopUs=(SystemClock.elapsedRealtime() * 1000) - stopTimestampUs;
    long framesSinceStop=(elapsedTimeSinceStopUs * outputSampleRate) / C.MICROS_PER_SECOND;
    return Math.min(endPlaybackHeadPosition,stopPlaybackHeadPosition + framesSinceStop);
  }
  int state=audioTrack.getPlayState();
  if (state == PLAYSTATE_STOPPED) {
    return 0;
  }
  long rawPlaybackHeadPosition=0xFFFFFFFFL & audioTrack.getPlaybackHeadPosition();
  if (needsPassthroughWorkarounds) {
    if (state == PLAYSTATE_PAUSED && rawPlaybackHeadPosition == 0) {
      passthroughWorkaroundPauseOffset=lastRawPlaybackHeadPosition;
    }
    rawPlaybackHeadPosition+=passthroughWorkaroundPauseOffset;
  }
  if (Util.SDK_INT <= 28) {
    if (rawPlaybackHeadPosition == 0 && lastRawPlaybackHeadPosition > 0 && state == PLAYSTATE_PLAYING) {
      if (forceResetWorkaroundTimeMs == C.TIME_UNSET) {
        forceResetWorkaroundTimeMs=SystemClock.elapsedRealtime();
      }
      return lastRawPlaybackHeadPosition;
    }
 else {
      forceResetWorkaroundTimeMs=C.TIME_UNSET;
    }
  }
  if (lastRawPlaybackHeadPosition > rawPlaybackHeadPosition) {
    rawPlaybackHeadWrapCount++;
  }
  lastRawPlaybackHeadPosition=rawPlaybackHeadPosition;
  return rawPlaybackHeadPosition + (rawPlaybackHeadWrapCount << 32);
}

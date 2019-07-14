/** 
 * If passthrough workarounds are enabled, pausing is implemented by forcing the AudioTrack to underrun. In this case, still behave as if we have pending data, otherwise writing won't resume.
 */
private boolean forceHasPendingData(){
  return needsPassthroughWorkarounds && Assertions.checkNotNull(audioTrack).getPlayState() == AudioTrack.PLAYSTATE_PAUSED && getPlaybackHeadPosition() == 0;
}

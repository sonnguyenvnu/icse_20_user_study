/** 
 * Checks the state of the audio track and returns whether the caller can write data to the track. Notifies  {@link Listener#onUnderrun(int,long)} if the track has underrun.
 * @param writtenFrames The number of frames that have been written.
 * @return Whether the caller can write data to the track.
 */
public boolean mayHandleBuffer(long writtenFrames){
  @PlayState int playState=Assertions.checkNotNull(audioTrack).getPlayState();
  if (needsPassthroughWorkarounds) {
    if (playState == PLAYSTATE_PAUSED) {
      hasData=false;
      return false;
    }
    if (playState == PLAYSTATE_STOPPED && getPlaybackHeadPosition() == 0) {
      return false;
    }
  }
  boolean hadData=hasData;
  hasData=hasPendingData(writtenFrames);
  if (hadData && !hasData && playState != PLAYSTATE_STOPPED && listener != null) {
    listener.onUnderrun(bufferSize,C.usToMs(bufferSizeUs));
  }
  return true;
}

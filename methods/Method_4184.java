private void maybePollAndCheckTimestamp(long systemTimeUs,long playbackPositionUs){
  AudioTimestampPoller audioTimestampPoller=Assertions.checkNotNull(this.audioTimestampPoller);
  if (!audioTimestampPoller.maybePollTimestamp(systemTimeUs)) {
    return;
  }
  long audioTimestampSystemTimeUs=audioTimestampPoller.getTimestampSystemTimeUs();
  long audioTimestampPositionFrames=audioTimestampPoller.getTimestampPositionFrames();
  if (Math.abs(audioTimestampSystemTimeUs - systemTimeUs) > MAX_AUDIO_TIMESTAMP_OFFSET_US) {
    listener.onSystemTimeUsMismatch(audioTimestampPositionFrames,audioTimestampSystemTimeUs,systemTimeUs,playbackPositionUs);
    audioTimestampPoller.rejectTimestamp();
  }
 else   if (Math.abs(framesToDurationUs(audioTimestampPositionFrames) - playbackPositionUs) > MAX_AUDIO_TIMESTAMP_OFFSET_US) {
    listener.onPositionFramesMismatch(audioTimestampPositionFrames,audioTimestampSystemTimeUs,systemTimeUs,playbackPositionUs);
    audioTimestampPoller.rejectTimestamp();
  }
 else {
    audioTimestampPoller.acceptTimestamp();
  }
}

private void maybeSampleSyncParams(){
  long playbackPositionUs=getPlaybackHeadPositionUs();
  if (playbackPositionUs == 0) {
    return;
  }
  long systemTimeUs=System.nanoTime() / 1000;
  if (systemTimeUs - lastPlayheadSampleTimeUs >= MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US) {
    playheadOffsets[nextPlayheadOffsetIndex]=playbackPositionUs - systemTimeUs;
    nextPlayheadOffsetIndex=(nextPlayheadOffsetIndex + 1) % MAX_PLAYHEAD_OFFSET_COUNT;
    if (playheadOffsetCount < MAX_PLAYHEAD_OFFSET_COUNT) {
      playheadOffsetCount++;
    }
    lastPlayheadSampleTimeUs=systemTimeUs;
    smoothedPlayheadOffsetUs=0;
    for (int i=0; i < playheadOffsetCount; i++) {
      smoothedPlayheadOffsetUs+=playheadOffsets[i] / playheadOffsetCount;
    }
  }
  if (needsPassthroughWorkarounds) {
    return;
  }
  maybePollAndCheckTimestamp(systemTimeUs,playbackPositionUs);
  maybeUpdateLatency(systemTimeUs);
}

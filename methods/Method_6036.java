@Override protected void onPositionReset(long positionUs,boolean joining) throws ExoPlaybackException {
  super.onPositionReset(positionUs,joining);
  clearRenderedFirstFrame();
  initialPositionUs=C.TIME_UNSET;
  consecutiveDroppedFrameCount=0;
  lastInputTimeUs=C.TIME_UNSET;
  if (pendingOutputStreamOffsetCount != 0) {
    outputStreamOffsetUs=pendingOutputStreamOffsetsUs[pendingOutputStreamOffsetCount - 1];
    pendingOutputStreamOffsetCount=0;
  }
  if (joining) {
    setJoiningDeadlineMs();
  }
 else {
    joiningDeadlineMs=C.TIME_UNSET;
  }
}

@Override protected void onPositionReset(long positionUs,boolean joining) throws ExoPlaybackException {
  super.onPositionReset(positionUs,joining);
  audioSink.flush();
  currentPositionUs=positionUs;
  allowFirstBufferPositionDiscontinuity=true;
  allowPositionDiscontinuity=true;
  lastInputTimeUs=C.TIME_UNSET;
  pendingStreamChangeCount=0;
}

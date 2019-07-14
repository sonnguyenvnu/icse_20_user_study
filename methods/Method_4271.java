@Override protected void onPositionReset(long positionUs,boolean joining) throws ExoPlaybackException {
  audioSink.flush();
  currentPositionUs=positionUs;
  allowFirstBufferPositionDiscontinuity=true;
  allowPositionDiscontinuity=true;
  inputStreamEnded=false;
  outputStreamEnded=false;
  if (decoder != null) {
    flushDecoder();
  }
}

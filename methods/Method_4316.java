@Override public final void resetPosition(long positionUs) throws ExoPlaybackException {
  streamIsFinal=false;
  readEndOfStream=false;
  onPositionReset(positionUs,false);
}

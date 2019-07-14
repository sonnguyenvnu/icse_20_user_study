@Override public final void resetPosition(long positionUs) throws ExoPlaybackException {
  streamIsFinal=false;
  onPositionReset(positionUs,false);
}

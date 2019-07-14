@Override public final void enable(RendererConfiguration configuration,Format[] formats,SampleStream stream,long positionUs,boolean joining,long offsetUs) throws ExoPlaybackException {
  Assertions.checkState(state == STATE_DISABLED);
  this.configuration=configuration;
  state=STATE_ENABLED;
  onEnabled(joining);
  replaceStream(formats,stream,offsetUs);
  onPositionReset(positionUs,joining);
}

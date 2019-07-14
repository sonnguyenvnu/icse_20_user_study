/** 
 * Replaces the  {@link SampleStream} that will be associated with this renderer.<p> This method may be called when the renderer is in the following states: {@link #STATE_DISABLED}.
 * @param configuration The renderer configuration.
 * @param formats The enabled formats. Should be empty.
 * @param stream The {@link SampleStream} from which the renderer should consume.
 * @param positionUs The player's current position.
 * @param joining Whether this renderer is being enabled to join an ongoing playback.
 * @param offsetUs The offset that should be subtracted from {@code positionUs}to get the playback position with respect to the media.
 * @throws ExoPlaybackException If an error occurs.
 */
@Override public final void enable(RendererConfiguration configuration,Format[] formats,SampleStream stream,long positionUs,boolean joining,long offsetUs) throws ExoPlaybackException {
  Assertions.checkState(state == STATE_DISABLED);
  this.configuration=configuration;
  state=STATE_ENABLED;
  onEnabled(joining);
  replaceStream(formats,stream,offsetUs);
  onPositionReset(positionUs,joining);
}
